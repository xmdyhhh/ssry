package com.ruoyi.ssry.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.Grade;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.service.IGradeService;
import com.ruoyi.ssry.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ssry/grade")
public class GradeController extends BaseController {

    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IStudentService studentService;

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
}
