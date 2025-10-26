package com.ruoyi.ssry.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.mapper.CourseMapper;
import com.ruoyi.ssry.mapper.EnrollmentMapper;
import com.ruoyi.ssry.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {

    @Autowired
    private EnrollmentMapper enrollmentMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getSelectedCourses(String studentid) {
        return enrollmentMapper.getSelectedCourses(studentid);
    }

    @Override
    public List<Course> getAvailableCourses(String studentid) {
        return enrollmentMapper.getAvailableCourses(studentid);
    }


    public AjaxResult selectCourse(String studentId, String courseId) {
        Course course = courseMapper.selectCourseById(courseId);
        if(course.getCurrentEnrollment() >= course.getMaxEnrollment()){
            return AjaxResult.error("选课失败，该课程已满");
        }
        course.setCurrentEnrollment(course.getCurrentEnrollment() + 1);
        courseMapper.updateCourse(course);
        int result = enrollmentMapper.selectCourse(studentId, courseId);
        if (result > 0) {
            return AjaxResult.success("选课成功");
        } else {
            return AjaxResult.error("选课失败，请检查课程是否已存在");
        }
    }

    public AjaxResult exitCourse(String studentId, String courseId) {
        Course course = courseMapper.selectCourseById(courseId);
        course.setCurrentEnrollment(course.getCurrentEnrollment() - 1);
        courseMapper.updateCourse(course);
        int result = enrollmentMapper.exitCourse(studentId, courseId);
        if (result > 0) {
            return AjaxResult.success("退课成功");
        } else {
            return AjaxResult.error("退课失败");
        }
    }
}
