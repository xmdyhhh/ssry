package com.ruoyi.ssry.mapper;

import com.ruoyi.ssry.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface EnrollmentMapper {

    List<Course> getSelectedCourses(String studentno);

    List<Course> getAvailableCourses(String studentno);

    int selectCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);

    int exitCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);

}
