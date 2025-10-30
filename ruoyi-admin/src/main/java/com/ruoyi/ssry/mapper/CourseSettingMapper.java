package com.ruoyi.ssry.mapper;

import com.ruoyi.ssry.domain.CourseSettingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseSettingMapper {
    List<CourseSettingVO> list();
}
