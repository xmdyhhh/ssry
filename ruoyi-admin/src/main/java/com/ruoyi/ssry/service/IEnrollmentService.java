package com.ruoyi.ssry.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.ssry.domain.Course;

import java.util.List;

public interface IEnrollmentService {

    public List<Course> getSelectedCourses(String studentid);

    public List<Course> getAvailableCourses(String studentid);

    AjaxResult selectCourse(String studentId,String courseId);

    AjaxResult exitCourse(String studentId,String courseId);
}
