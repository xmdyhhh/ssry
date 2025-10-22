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
import com.ruoyi.studentSys.domain.SysDistrict;
import com.ruoyi.studentSys.service.ISysDistrictService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地区信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Controller
@RequestMapping("/studentSys/district")
public class SysDistrictController extends BaseController
{
    private String prefix = "studentSys/district";

    @Autowired
    private ISysDistrictService sysDistrictService;

    @RequiresPermissions("studentSys:district:view")
    @GetMapping()
    public String district()
    {
        return prefix + "/district";
    }

    /**
     * 查询地区信息列表
     */
    @RequiresPermissions("studentSys:district:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysDistrict sysDistrict)
    {
        startPage();
        List<SysDistrict> list = sysDistrictService.selectSysDistrictList(sysDistrict);
        return getDataTable(list);
    }

    /**
     * 导出地区信息列表
     */
    @RequiresPermissions("studentSys:district:export")
    @Log(title = "地区信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDistrict sysDistrict)
    {
        List<SysDistrict> list = sysDistrictService.selectSysDistrictList(sysDistrict);
        ExcelUtil<SysDistrict> util = new ExcelUtil<SysDistrict>(SysDistrict.class);
        return util.exportExcel(list, "地区信息数据");
    }

    /**
     * 新增地区信息
     */
    @RequiresPermissions("studentSys:district:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存地区信息
     */
    @RequiresPermissions("studentSys:district:add")
    @Log(title = "地区信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysDistrict sysDistrict)
    {
        return toAjax(sysDistrictService.insertSysDistrict(sysDistrict));
    }

    /**
     * 修改地区信息
     */
    @RequiresPermissions("studentSys:district:edit")
    @GetMapping("/edit/{districtId}")
    public String edit(@PathVariable("districtId") String districtId, ModelMap mmap)
    {
        SysDistrict sysDistrict = sysDistrictService.selectSysDistrictByDistrictId(districtId);
        mmap.put("sysDistrict", sysDistrict);
        return prefix + "/edit";
    }

    /**
     * 修改保存地区信息
     */
    @RequiresPermissions("studentSys:district:edit")
    @Log(title = "地区信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysDistrict sysDistrict)
    {
        return toAjax(sysDistrictService.updateSysDistrict(sysDistrict));
    }

    /**
     * 删除地区信息
     */
    @RequiresPermissions("studentSys:district:remove")
    @Log(title = "地区信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysDistrictService.deleteSysDistrictByDistrictIds(ids));
    }
}
