package com.ruoyi.studentSys.controller;

import java.util.List;

import com.ruoyi.studentSys.domain.TCls;
import com.ruoyi.studentSys.service.ITClsService;
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
import com.ruoyi.studentSys.domain.TStudent;
import com.ruoyi.studentSys.service.ITStudentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生信息Controller
 *
 * @author ruoyi
 * @date 2025-09-22
 */
@Controller
@RequestMapping("/studentSys/student")
public class TStudentController extends BaseController
{
    private String prefix = "studentSys/student";

    @Autowired
    private ITStudentService tStudentService;

    @Autowired
    private ITClsService tClsService;

    @RequiresPermissions("studentSys:student:view")
    @GetMapping()
    public String student(ModelMap mmap)
    {
        List<TCls> clsList = tClsService.selectTClsList(new TCls());
        mmap.put("clsList", clsList);
        return prefix + "/student";
    }

    /**
     * 查询学生信息列表
     */
    @RequiresPermissions("studentSys:student:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TStudent tStudent)
    {
        startPage();
        List<TStudent> list = tStudentService.selectTStudentList(tStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生信息列表
     */
    @RequiresPermissions("studentSys:student:export")
    @Log(title = "学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TStudent tStudent)
    {
        List<TStudent> list = tStudentService.selectTStudentList(tStudent);
        ExcelUtil<TStudent> util = new ExcelUtil<TStudent>(TStudent.class);
        return util.exportExcel(list, "学生信息数据");
    }

    /**
     * 新增学生信息
     */
    @RequiresPermissions("studentSys:student:add")
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<TCls> clsList = tClsService.selectTClsList(new TCls());
        mmap.put("clsList", clsList);
        return prefix + "/add";
    }

    /**
     * 新增保存学生信息
     */
    @RequiresPermissions("studentSys:student:add")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TStudent tStudent)
    {
        return toAjax(tStudentService.insertTStudent(tStudent));
    }

    /**
     * 修改学生信息
     */
    @RequiresPermissions("studentSys:student:edit")
    @GetMapping("/edit/{stuId}")
    public String edit(@PathVariable("stuId") String stuId, ModelMap mmap)
    {
        TStudent tStudent = tStudentService.selectTStudentByStuId(stuId);
        mmap.put("tStudent", tStudent);
        List<TCls> clsList = tClsService.selectTClsList(new TCls());
        mmap.put("clsList", clsList);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生信息
     */
    @RequiresPermissions("studentSys:student:edit")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TStudent tStudent)
    {
        return toAjax(tStudentService.updateTStudent(tStudent));
    }

    /**
     * 删除学生信息
     */
    @RequiresPermissions("studentSys:student:remove")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tStudentService.deleteTStudentByStuIds(ids));
    }
}
