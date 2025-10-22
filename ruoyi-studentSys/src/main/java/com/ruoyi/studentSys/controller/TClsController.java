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
import com.ruoyi.studentSys.domain.TCls;
import com.ruoyi.studentSys.service.ITClsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
@Controller
@RequestMapping("/studentSys/Cls")
public class TClsController extends BaseController
{
    private String prefix = "studentSys/Cls";

    @Autowired
    private ITClsService tClsService;

    @RequiresPermissions("studentSys:Cls:view")
    @GetMapping()
    public String Cls()
    {
        return prefix + "/Cls";
    }

    /**
     * 查询班级信息列表
     */
    @RequiresPermissions("studentSys:Cls:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TCls tCls)
    {
        startPage();
        List<TCls> list = tClsService.selectTClsList(tCls);
        return getDataTable(list);
    }

    /**
     * 导出班级信息列表
     */
    @RequiresPermissions("studentSys:Cls:export")
    @Log(title = "班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TCls tCls)
    {
        List<TCls> list = tClsService.selectTClsList(tCls);
        ExcelUtil<TCls> util = new ExcelUtil<TCls>(TCls.class);
        return util.exportExcel(list, "班级信息数据");
    }

    /**
     * 新增班级信息
     */
    @RequiresPermissions("studentSys:Cls:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存班级信息
     */
    @RequiresPermissions("studentSys:Cls:add")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TCls tCls)
    {
        return toAjax(tClsService.insertTCls(tCls));
    }

    /**
     * 修改班级信息
     */
    @RequiresPermissions("studentSys:Cls:edit")
    @GetMapping("/edit/{clsId}")
    public String edit(@PathVariable("clsId") String clsId, ModelMap mmap)
    {
        TCls tCls = tClsService.selectTClsByClsId(clsId);
        mmap.put("tCls", tCls);
        return prefix + "/edit";
    }

    /**
     * 修改保存班级信息
     */
    @RequiresPermissions("studentSys:Cls:edit")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TCls tCls)
    {
        return toAjax(tClsService.updateTCls(tCls));
    }

    /**
     * 删除班级信息
     */
    @RequiresPermissions("studentSys:Cls:remove")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tClsService.deleteTClsByClsIds(ids));
    }
}
