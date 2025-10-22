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
import com.ruoyi.studentSys.domain.TScore;
import com.ruoyi.studentSys.service.ITScoreService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成绩信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-29
 */
@Controller
@RequestMapping("/studentSys/score")
public class TScoreController extends BaseController
{
    private String prefix = "studentSys/score";

    @Autowired
    private ITScoreService tScoreService;

    @RequiresPermissions("studentSys:score:view")
    @GetMapping()
    public String score()
    {
        return prefix + "/score";
    }

    /**
     * 查询成绩信息列表
     */
    @RequiresPermissions("studentSys:score:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TScore tScore)
    {
        startPage();
        List<TScore> list = tScoreService.selectTScoreList(tScore);
        return getDataTable(list);
    }

    /**
     * 导出成绩信息列表
     */
    @RequiresPermissions("studentSys:score:export")
    @Log(title = "成绩信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TScore tScore)
    {
        List<TScore> list = tScoreService.selectTScoreList(tScore);
        ExcelUtil<TScore> util = new ExcelUtil<TScore>(TScore.class);
        return util.exportExcel(list, "成绩信息数据");
    }

    /**
     * 新增成绩信息
     */
    @RequiresPermissions("studentSys:score:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成绩信息
     */
    @RequiresPermissions("studentSys:score:add")
    @Log(title = "成绩信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TScore tScore)
    {
        return toAjax(tScoreService.insertTScore(tScore));
    }

    /**
     * 修改成绩信息
     */
    @RequiresPermissions("studentSys:score:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TScore tScore = tScoreService.selectTScoreById(id);
        mmap.put("tScore", tScore);
        return prefix + "/edit";
    }

    /**
     * 修改保存成绩信息
     */
    @RequiresPermissions("studentSys:score:edit")
    @Log(title = "成绩信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TScore tScore)
    {
        return toAjax(tScoreService.updateTScore(tScore));
    }

    /**
     * 删除成绩信息
     */
    @RequiresPermissions("studentSys:score:remove")
    @Log(title = "成绩信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tScoreService.deleteTScoreByIds(ids));
    }
}
