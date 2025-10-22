package com.ruoyi.studentSys.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.studentSys.domain.TCourse;
import com.ruoyi.studentSys.service.ITCourseService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-29
 */
@Controller
@RequestMapping("/studentSys/course")
public class TCourseController extends BaseController
{
    private String prefix = "studentSys/course";

    @Autowired
    private ITCourseService tCourseService;

    @RequiresPermissions("studentSys:course:view")
    @GetMapping()
    public String course()
    {
        return prefix + "/course";
    }

    /**
     * 查询课程信息列表
     */
    @RequiresPermissions("studentSys:course:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TCourse tCourse)
    {
        startPage();
        List<TCourse> list = tCourseService.selectTCourseList(tCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程信息列表
     */
    @RequiresPermissions("studentSys:course:export")
    @Log(title = "课程信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TCourse tCourse)
    {
        List<TCourse> list = tCourseService.selectTCourseList(tCourse);
        ExcelUtil<TCourse> util = new ExcelUtil<TCourse>(TCourse.class);
        return util.exportExcel(list, "课程信息数据");
    }

    /**
     * 新增课程信息
     */
    @RequiresPermissions("studentSys:course:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程信息
     */
    @RequiresPermissions("studentSys:course:add")
    @Log(title = "课程信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TCourse tCourse)
    {
        return toAjax(tCourseService.insertTCourse(tCourse));
    }

    /**
     * 修改课程信息
     */
    @RequiresPermissions("studentSys:course:edit")
    @GetMapping("/edit/{courseId}")
    public String edit(@PathVariable("courseId") String courseId, ModelMap mmap)
    {
        TCourse tCourse = tCourseService.selectTCourseByCourseId(courseId);
        mmap.put("tCourse", tCourse);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程信息
     */
    @RequiresPermissions("studentSys:course:edit")
    @Log(title = "课程信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TCourse tCourse)
    {
        return toAjax(tCourseService.updateTCourse(tCourse));
    }

    /**
     * 删除课程信息
     */
    @RequiresPermissions("studentSys:course:remove")
    @Log(title = "课程信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tCourseService.deleteTCourseByCourseIds(ids));
    }
}
