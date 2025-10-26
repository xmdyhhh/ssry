package com.ruoyi.ssry.mapper;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.ssry.domain.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// GradeMapper.java
public interface GradeMapper {

    List<Grade> studentgradelist(String studentid);
}
