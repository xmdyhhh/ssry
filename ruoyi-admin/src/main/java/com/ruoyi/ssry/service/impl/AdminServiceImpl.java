package com.ruoyi.ssry.service.impl;

import com.ruoyi.ssry.domain.Admin;
import com.ruoyi.ssry.mapper.AdminMapper;
import com.ruoyi.ssry.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin selectAdmin(String loginName) {
        return adminMapper.selectAdmin(loginName);
    }
}
