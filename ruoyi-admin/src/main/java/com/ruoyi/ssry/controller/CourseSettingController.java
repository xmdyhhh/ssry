package com.ruoyi.ssry.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.ssry.domain.College;
import com.ruoyi.ssry.domain.CourseSettingVO;
import com.ruoyi.ssry.domain.SaveAllowedCollegesDTO;
import com.ruoyi.ssry.service.ICollegeService;
import com.ruoyi.ssry.service.ICourseSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/toAllowedColleges")
    public String toAllowedColleges() {
        return "ssry/coursesetting/allowedColleges"; // 返回 HTML 模板
    }

    @GetMapping("/listColleges")
    @ResponseBody
    public AjaxResult listColleges() {
        List<College> colleges = collegeService.selectCollegeList(new College());
        return AjaxResult.success(colleges);
    }

    @PostMapping("/saveAllowedColleges")
    @ResponseBody
    public AjaxResult saveAllowedColleges(@RequestBody SaveAllowedCollegesDTO dto) {
        courseSettingService.saveAllowedColleges(dto.getCourseIds(), dto.getCollegeIds());
        return AjaxResult.success("设置成功");
    }
}
