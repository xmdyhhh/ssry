package com.ruoyi.ssry.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.*;
import com.ruoyi.ssry.service.ICourseService;
import com.ruoyi.ssry.service.IGradeService;
import com.ruoyi.ssry.service.IStudentService;
import com.ruoyi.ssry.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ssry/grade")
public class GradeController extends BaseController {

    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private ICourseService courseService;

    @GetMapping("/studentgrade")
    public String studentgrade() {
        return "/ssry/grade/studentgrade";
    }

    @ResponseBody
    @GetMapping("/studentgradelist")
    public AjaxResult list() {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Student student = studentService.selectStudentBystudentno(loginName);
        List<Grade> list = gradeService.studentgradelist(student.getId());
        return AjaxResult.success(list);
    }

    @GetMapping("/tolist")
    public String tolist() {
        return "/ssry/grade/list";
    }

    @ResponseBody
    @GetMapping("/tsgradelist")
    public AjaxResult tslist(@RequestParam String courseId) {
        List<Grade> list = gradeService.getcourselist(courseId);
        return AjaxResult.success(list);
    }

    /**
     * 更新成绩（由前端 editScore 调用）
     */
    @PostMapping("/updategrade")
    @ResponseBody
    @Log(title = "成绩管理", businessType = BusinessType.UPDATE)
    public AjaxResult updateGrade(@RequestBody Grade grade) {
        try {
            gradeService.updateGrade(grade);
            return AjaxResult.success("修改成功");
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @GetMapping("/coursecj")
    public String coursecj(ModelMap mmap) {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Teacher teacher = teacherService.selectTeacherByteacherno(loginName);
        List<Course> courses = teacherService.getcourselist(teacher.getId());
        mmap.put("courses", courses);
        return "/ssry/grade/coursecj";
    }

    @PostMapping("/gradepaibyteachercourse")
    @ResponseBody
    public AjaxResult gradepaibyteachercourse(String courseId) {
        List<TJInt> list = gradeService.tsgradelist(courseId);
        AjaxResult success = AjaxResult.success();
        success.put("listdata", list);
        return success;
    }

    @PostMapping("/getzhubycourseid")
    @ResponseBody
    public AjaxResult getzhubycourseid(String courseId) {
        AjaxResult result = gradeService.getzhubycourseid(courseId);
        return result;
    }

    @PostMapping("/getCourseYearlyAvg")
    @ResponseBody
    public AjaxResult getCourseYearlyAvg(String courseId) {
        AjaxResult result = gradeService.getCourseYearlyAvg(courseId);
        return result;
    }

    @PostMapping("/course-editable")
    @ResponseBody
    public AjaxResult isCourseEditable(@RequestParam String courseId) {
        Course course = courseService.selectCourseById(courseId);
        if (course == null) {
            return AjaxResult.error("课程不存在");
        }

        Date endDate = course.getEndDate();
        if (endDate == null) {
            return AjaxResult.success().put("editable", true);
        }

        LocalDate now = LocalDate.now();
        LocalDate courseEndDate = endDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        boolean editable = !now.isAfter(courseEndDate);

        return AjaxResult.success()
                .put("editable", editable)
                .put("courseName", course.getCourseName())
                .put("endDate", course.getEndDate());
    }
}
