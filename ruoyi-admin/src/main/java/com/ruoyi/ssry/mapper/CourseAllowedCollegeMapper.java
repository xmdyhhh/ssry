package com.ruoyi.ssry.mapper;

import com.ruoyi.ssry.domain.CourseAllowedCollege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseAllowedCollegeMapper {

    void deleteByCourseIds(@Param("courseIds") Long[] courseIds);

    void batchInsert(List<CourseAllowedCollege> relations);
}
