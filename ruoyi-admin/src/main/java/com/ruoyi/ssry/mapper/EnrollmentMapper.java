package com.ruoyi.ssry.mapper;

import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.domain.Enrollment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface EnrollmentMapper {

    List<Course> getSelectedCourses(String studentno);

    List<Course> getAvailableCourses(String studentno);

    int selectCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);

    int exitCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);

    void addenrollment(@Param("studentId") String  studentid, @Param("courseId") String courseid,@Param("status") String pending);

    void delenrollment(@Param("courseId") String courseid);

    void batchInsertEnrollments(@Param("enrollments") List<Enrollment> enrollments);

    void deleteByCourseId(@Param("courseId") String courseId);
}
