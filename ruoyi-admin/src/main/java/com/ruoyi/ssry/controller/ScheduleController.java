package com.ruoyi.ssry.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.domain.Teacher;
import com.ruoyi.ssry.service.IEnrollmentService;
import com.ruoyi.ssry.service.IStudentService;
import com.ruoyi.ssry.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ssry/schedule")
public class ScheduleController extends BaseController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private IEnrollmentService enrollmentService;
    @Autowired
    private ITeacherService  teacherService;

    @GetMapping("/studentschedule")
    public String schedule() {
        return "ssry/schedule/studentschedule";
    }

    @ResponseBody
    @GetMapping("/studentschedulelist")
    public AjaxResult studentschedulelist() {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Student student = studentService.selectStudentBystudentno(loginName);
        List<Course> courses = enrollmentService.getSelectedCourses(student.getId());
        return AjaxResult.success(courses);
    }

    @GetMapping("/teacherschedule")
    public String teacherschedule() {
        return "ssry/schedule/teacherschedule";
    }

    @ResponseBody
    @GetMapping("/teacherschedulelist")
    public AjaxResult teacherschedulelist() {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Teacher teacher = teacherService.selectTeacherByteacherno(loginName);
        List<Course> courses = teacherService.getcourselist(teacher.getId());
        return AjaxResult.success(courses);
    }
}