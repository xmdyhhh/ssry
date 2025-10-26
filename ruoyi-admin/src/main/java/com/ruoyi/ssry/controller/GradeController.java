package com.ruoyi.ssry.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.Grade;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.service.IGradeService;
import com.ruoyi.ssry.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ssry/grade")
public class GradeController extends BaseController {

    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IStudentService studentService;

    @GetMapping("/studentgrade")
    public String studentgrade(){
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
}
