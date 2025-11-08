package com.ruoyi.ssry.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ssry.domain.CourseAddDTO;
import com.ruoyi.ssry.domain.CourseAllowedCollege;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.mapper.EnrollmentMapper;
import com.ruoyi.ssry.mapper.StudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ssry.mapper.CourseMapper;
import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.service.ICourseService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-10-22
 */
@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @Override
    public Course selectCourseById(String id) {
        return courseMapper.selectCourseById(id);
    }

    @Override
    public List<Course> selectCourseList(Course course) {
        return courseMapper.selectCourseList(course);
    }

    @Override
    public int insertCourse(Course course) {
        course.setCreateTime(DateUtils.getNowDate());
        return courseMapper.insertCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        course.setUpdateTime(DateUtils.getNowDate());
        return courseMapper.updateCourse(course);
    }

    @Override
    public int deleteCourseByIds(String ids) {
        return courseMapper.deleteCourseByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteCourseById(String id) {
        return courseMapper.deleteCourseById(id);
    }

    /**
     * 新增课程，支持设置允许选课的学院白名单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addCourse(CourseAddDTO dto) {
        try {
            Course course = new Course();
            BeanUtils.copyProperties(dto, course);
            course.setCreateTime(DateUtils.getNowDate());
            course.setUpdateTime(DateUtils.getNowDate());
            courseMapper.insertCourse(course);
            if (Boolean.TRUE.equals(dto.getCollegeRestricted()) && dto.getAllowedCollegeIds() != null) {
                List<CourseAllowedCollege> relations = dto.getAllowedCollegeIds().stream()
                        .filter(StringUtils::isNotEmpty)
                        .map(collegeId -> {
                            CourseAllowedCollege rel = new CourseAllowedCollege();
                            rel.setCourseId(course.getId());
                            rel.setCollegeId(collegeId);
                            return rel;
                        })
                        .collect(Collectors.toList());

                if (!relations.isEmpty()) {
                    courseMapper.batchInsertAllowedColleges(relations);
                }
                List<Student> students = studentMapper.getStudentsByCollegeId(dto.getAllowedCollegeIds());
                for (Student student : students) {
                    enrollmentMapper.addenrollment(student.getId(), course.getId(),"PENDING");
                }
            }
            return AjaxResult.success("课程新增成功");
        } catch (Exception e) {
            return AjaxResult.error("新增失败：" + e.getMessage());
        }
    }

    @Override
    public List<Course> selectenrolledCourseBystudentId(String studentId) {
        return courseMapper.selectenrolledCourseBystudentId(studentId);
    }

}