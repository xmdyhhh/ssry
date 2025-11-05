package com.ruoyi.ssry.mapper;

import com.ruoyi.ssry.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin selectAdmin(String loginName);
}
