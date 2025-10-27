package com.ruoyi.ssry.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.domain.Grade;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.domain.Teacher;
import com.ruoyi.ssry.service.ITeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ssry/ts")
public class TeacherCourseController {

    @Autowired
    private ITeacherService teacherService;

    @RequestMapping("/teachercourse")
    public String tc(){
        return "ssry/teacherCourse/teachercourselist";
    }

    @ResponseBody
    @RequestMapping("/teachercourselist")
    public AjaxResult tclist(){
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Teacher teacher = teacherService.selectTeacherByteacherno(loginName);
        List<Course> list = teacherService.getcourselist(teacher.getId());
        return AjaxResult.success(list);
    }
}
