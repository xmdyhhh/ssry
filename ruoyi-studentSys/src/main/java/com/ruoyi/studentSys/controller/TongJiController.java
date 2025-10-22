package com.ruoyi.studentSys.controller;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.studentSys.domain.TCourse;
import com.ruoyi.studentSys.domain.TongJiInt;
import com.ruoyi.studentSys.service.ITCourseService;
import com.ruoyi.studentSys.service.ITScoreService;
import com.ruoyi.studentSys.service.ITStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TongJiController extends BaseController {

    @Autowired
    private ITScoreService scoreService;
    @Autowired
    private ITCourseService courseService;
    @Autowired
    private ITStudentService studentService;

    @RequestMapping("/cjtj")
    public String chengjitongji(ModelMap mmap) {

        List<TCourse> courses = courseService.selectTCourseList(new TCourse());
        mmap.put("courses", courses);
        List<TongJiInt> tongJiInts = studentService.selectCountByProvince();
        mmap.put("provinceList", tongJiInts);


        int max = tongJiInts.stream()
                .mapToInt(TongJiInt::getValue)
                .max()
                .orElse(-1);


        mmap.put("maxCount", max);
        return "studentSys/tongji/chengjitongji";
    }

    @PostMapping("/getRangeByCourseId")
    @ResponseBody
    public AjaxResult chengjitongji(String courseId)
    {
        List<TongJiInt> list = scoreService.selectRangeByCourseId(courseId);
        AjaxResult success = AjaxResult.success();
        success.put("listdata", list);
        return success;
    }


    @PostMapping("/getAvgByCourseId")
    @ResponseBody
    public AjaxResult getAvgByCourseId(String courseId)
    {
        AjaxResult result = scoreService.selectClsAvgByCourseId(courseId);
        return result;
    }

    @PostMapping("/getCountByProvince")
    @ResponseBody
    public AjaxResult getCountByProvince()
    {
        List<TongJiInt> tongJiInts = studentService.selectCountByProvince();
        AjaxResult result = AjaxResult.success();
        result.put("data", tongJiInts) ;
        return result;
    }
}