package com.ruoyi.ssry.service;

import com.ruoyi.ssry.domain.Admin;

import java.util.List;

public interface IAdminService {
    Admin selectAdmin(String loginName);

    List<Admin> list();
}
