package com.ruoyi.ssry.mapper;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.ssry.domain.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GradeMapper {

    List<Grade> studentgradelist(String studentid);

    List<Grade> getcourselist(String courseId);

    int updateGrade(Grade grade);

    Grade selectGradeById(Long id);

    public  Integer  selectRangeByCourseId (@Param("courseId") String courseId ,
                                            @Param("from")   Integer from ,
                                            @Param("to")   Integer to  );

    public  float  selectAvgByCourseId ( @Param("teacherId") String teacherId);
}