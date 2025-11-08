package com.ruoyi.ssry.service;

import com.ruoyi.ssry.domain.Homework;
import com.ruoyi.ssry.domain.Student;

import java.util.List;

public interface IHomeworkService {
    List<Homework> getStudentHomework();

    void addHomework(Homework  homework);

    boolean deleteHomeworkById(Long id);

    List<Student> getStudentListByHomeworkId(String homeworkId);

    Homework getHomeworkById(String homeworkId);
}
