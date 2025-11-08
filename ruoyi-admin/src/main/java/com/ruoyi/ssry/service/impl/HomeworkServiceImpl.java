package com.ruoyi.ssry.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.domain.Homework;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.mapper.HomeworkMapper;
import com.ruoyi.ssry.mapper.HomeworkSubmissionMapper;
import com.ruoyi.ssry.service.ICourseService;
import com.ruoyi.ssry.service.IHomeworkService;
import com.ruoyi.ssry.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeworkServiceImpl implements IHomeworkService {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private HomeworkSubmissionMapper submissionMapper;

    @Override
    public List<Homework> getStudentHomework() {
        // 1. 获取当前登录学生
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Student student = studentService.selectStudentBystudentno(loginName);
        // 2. 获取该学生所选课程
        List<Course> courses = courseService.selectenrolledCourseBystudentId(student.getId());
        // 3. 提取课程ID列表
        List<String> courseIds = courses.stream()
                .map(Course::getId)
                .collect(Collectors.toList());
        // 4. 查询这些课程下的所有作业
        return homeworkMapper.selectHomeworkByCourseIds(courseIds);
    }



    @Override
    public void addHomework(Homework homework) {
        homeworkMapper.addHomework(homework);
        Long courseId = homework.getCourseId();
        List<Student> students = studentService.selectStudentByCourseId(courseId);
        for (Student student : students) {
            submissionMapper.addnullSubmission(homework.getId(), Long.parseLong(student.getId()));
        }
    }

    @Override
    public boolean deleteHomeworkById(Long id) {
        submissionMapper.deleteByHomeworkId(id);
        return homeworkMapper.deleteHomeworkById(id) > 0;
    }

    @Override
    public List<Student> getStudentListByHomeworkId(String homeworkId) {
        return submissionMapper.getStudentListByHomeworkId(homeworkId);
    }

    @Override
    public Homework getHomeworkById(String homeworkId) {
        return homeworkMapper.selectHomeworkById(Long.parseLong(homeworkId));
    }

}
