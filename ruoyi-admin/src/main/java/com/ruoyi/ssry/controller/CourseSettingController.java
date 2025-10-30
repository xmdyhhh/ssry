package com.ruoyi.ssry.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.ssry.domain.College;
import com.ruoyi.ssry.domain.CourseSettingVO;
import com.ruoyi.ssry.service.ICollegeService;
import com.ruoyi.ssry.service.ICourseSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ssry/coursesetting")
public class CourseSettingController extends BaseController {

    @Autowired
    private ICourseSettingService courseSettingService;
    @Autowired
    private ICollegeService collegeService;

    @RequestMapping("/coursesetting")
    public String coursesetting(){
        return "ssry/coursesetting/coursesetting";
    }
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(){
        List<CourseSettingVO> list = courseSettingService.list();
        return AjaxResult.success(list);
    }
}
