package com.ruoyi.ssry.service.impl;

import com.ruoyi.ssry.domain.CourseAllowedCollege;
import com.ruoyi.ssry.domain.CourseSettingVO;
import com.ruoyi.ssry.domain.Enrollment;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.mapper.CourseAllowedCollegeMapper;
import com.ruoyi.ssry.mapper.CourseSettingMapper;
import com.ruoyi.ssry.mapper.EnrollmentMapper;
import com.ruoyi.ssry.mapper.StudentMapper;
import com.ruoyi.ssry.service.ICourseSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseSettingServiceImpl implements ICourseSettingService {

    @Autowired
    private CourseSettingMapper courseSettingMapper;
    @Autowired
    private EnrollmentMapper enrollmentMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<CourseSettingVO> list() {
        return courseSettingMapper.list();
    }

    @Autowired
    private CourseAllowedCollegeMapper courseAllowedCollegeMapper;

    @Override
    @Transactional
    public void saveAllowedColleges(Long[] courseIds, Long[] collegeIds) {
        if (courseIds == null || courseIds.length == 0) {
            return;
        }

        // 1. 删除旧的允许关系
        courseAllowedCollegeMapper.deleteByCourseIds(courseIds);

        if (collegeIds == null || collegeIds.length == 0) {
            // 如果没有指定学院，则仅清理允许关系，不添加新学生
            for (Long courseId : courseIds) {
                String cid = String.valueOf(courseId);
                enrollmentMapper.deleteByCourseId(cid); // 清空该课程所有 enrollment
            }
            return;
        }

        // 2. 插入新的允许关系
        List<CourseAllowedCollege> relations = new ArrayList<>();
        for (Long courseId : courseIds) {
            for (Long collegeId : collegeIds) {
                relations.add(new CourseAllowedCollege(String.valueOf(courseId), String.valueOf(collegeId)));
            }
        }
        if (!relations.isEmpty()) {
            courseAllowedCollegeMapper.batchInsert(relations);
        }

        // 3. 获取属于这些学院的学生
        List<String> collegeIdStrings = Arrays.stream(collegeIds)
                .map(String::valueOf)
                .collect(Collectors.toList());

        List<Student> students = studentMapper.getStudentsByCollegeId(collegeIdStrings);
        if (students == null || students.isEmpty()) {
            for (Long courseId : courseIds) {
                enrollmentMapper.deleteByCourseId(String.valueOf(courseId));
            }
            return;
        }

        // 4. 提取学生 ID 列表
        List<String> studentIds = students.stream()
                .map(Student::getId) // 返回 student_no（String）
                .collect(Collectors.toList());

        // 5. 遍历每个课程，执行：清空 + 重建
        for (Long courseId : courseIds) {
            String cid = String.valueOf(courseId);
            enrollmentMapper.deleteByCourseId(cid);
            List<Enrollment> toInsert = new ArrayList<>();
            for (String studentId : studentIds) {
                Enrollment e = new Enrollment();
                e.setStudentId(studentId);
                e.setCourseId(cid);
                e.setStatus("PENDING");
                toInsert.add(e);
            }

            if (!toInsert.isEmpty()) {
                enrollmentMapper.batchInsertEnrollments(toInsert);
            }
        }
    }
}
