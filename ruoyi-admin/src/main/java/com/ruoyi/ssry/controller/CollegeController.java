package com.ruoyi.ssry.controller;

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
import com.ruoyi.ssry.domain.College;
import com.ruoyi.ssry.service.ICollegeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学院信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-22
 */
@Controller
@RequestMapping("/ssry/college")
public class CollegeController extends BaseController
{
    private String prefix = "ssry/college";

    @Autowired
    private ICollegeService collegeService;

    @RequiresPermissions("ssry:college:view")
    @GetMapping()
    public String college()
    {
        return prefix + "/college";
    }

    /**
     * 查询学院信息列表
     */
    @RequiresPermissions("ssry:college:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(College college)
    {
        startPage();
        List<College> list = collegeService.selectCollegeList(college);
        return getDataTable(list);
    }

    /**
     * 导出学院信息列表
     */
    @RequiresPermissions("ssry:college:export")
    @Log(title = "学院信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(College college)
    {
        List<College> list = collegeService.selectCollegeList(college);
        ExcelUtil<College> util = new ExcelUtil<College>(College.class);
        return util.exportExcel(list, "学院信息数据");
    }

    /**
     * 新增学院信息
     */
    @RequiresPermissions("ssry:college:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学院信息
     */
    @RequiresPermissions("ssry:college:add")
    @Log(title = "学院信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(College college)
    {
        return toAjax(collegeService.insertCollege(college));
    }

    /**
     * 修改学院信息
     */
    @RequiresPermissions("ssry:college:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        College college = collegeService.selectCollegeById(id);
        mmap.put("college", college);
        return prefix + "/edit";
    }

    /**
     * 修改保存学院信息
     */
    @RequiresPermissions("ssry:college:edit")
    @Log(title = "学院信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(College college)
    {
        return toAjax(collegeService.updateCollege(college));
    }

    /**
     * 删除学院信息
     */
    @RequiresPermissions("ssry:college:remove")
    @Log(title = "学院信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(collegeService.deleteCollegeByIds(ids));
    }
}
