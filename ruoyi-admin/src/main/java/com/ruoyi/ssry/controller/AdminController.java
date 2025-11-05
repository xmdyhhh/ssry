package com.ruoyi.ssry.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.ssry.domain.Admin;
import com.ruoyi.ssry.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ssry/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        List<Admin> list = adminService.list();
        return AjaxResult.success(list);
    }
}
