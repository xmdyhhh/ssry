package com.ruoyi.ssry.controller;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.service.IEnrollmentService;
import com.ruoyi.ssry.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 选课管理 控制器
 *
 * @author ruoyi
 * @date 2025-09-22
 */
@Controller
@RequestMapping("/ssry/enrollment")
public class EnrollmentController {

    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private IStudentService studentService;

    // 用于 bootstrap-table 异步加载数据
    @ResponseBody
    @GetMapping("/selectedlist")
    public AjaxResult listSelectedCourses() {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            return AjaxResult.error("未登录");
        }

        String loginName = user.getLoginName();
        Student student = studentService.selectStudentBystudentno(loginName);
        if (student == null) {
            return AjaxResult.error("学生信息不存在");
        }

        List<Course> courses = enrollmentService.getSelectedCourses(student.getId());
        return AjaxResult.success(courses);
    }

    @GetMapping("/selected")
    public String selectedPage(ModelMap mmap) {
        return "ssry/enrollment/selectedlist";
    }

    @ResponseBody
    @GetMapping("/availablelist")
    public AjaxResult listAvailableCourses() {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            return AjaxResult.error("未登录");
        }
        String loginName = user.getLoginName();
        Student student = studentService.selectStudentBystudentno(loginName);
        if (student == null) {
            return AjaxResult.error("学生信息不存在");
        }
        List<Course> courses = enrollmentService.getAvailableCourses(student.getId());
        return AjaxResult.success(courses);
    }

    @GetMapping("/available")
    public String availablePage(ModelMap mmap) {
        return "ssry/enrollment/availablelist";
    }

    @PostMapping("/select")
    @ResponseBody
    public AjaxResult selectCourse(@RequestParam("courseId") String courseId) {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Student student = studentService.selectStudentBystudentno(loginName);
        return enrollmentService.selectCourse(student.getId(),courseId);
    }

    @PostMapping("/exit")
    @ResponseBody
    public AjaxResult exitCourse(@RequestParam("courseId") String courseId) {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Student student = studentService.selectStudentBystudentno(loginName);
        return enrollmentService.exitCourse(student.getId(),courseId);
    }
}