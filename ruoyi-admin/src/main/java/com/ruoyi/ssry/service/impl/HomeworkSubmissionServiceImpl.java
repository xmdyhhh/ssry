package com.ruoyi.ssry.service.impl;

import com.ruoyi.ssry.domain.Homework;
import com.ruoyi.ssry.domain.HomeworkSubmission;
import com.ruoyi.ssry.mapper.HomeworkMapper;
import com.ruoyi.ssry.mapper.HomeworkSubmissionMapper;
import com.ruoyi.ssry.service.IHomeworkSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class HomeworkSubmissionServiceImpl implements IHomeworkSubmissionService {

    @Autowired
    private HomeworkSubmissionMapper homeworkSubmissionMapper;
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public List<HomeworkSubmission> getSubmissionsByhomeworkId(String homeworkId) {
        return homeworkSubmissionMapper.getSubmissionsByhomeworkId(homeworkId);
    }

    @Override
    public void updateScore(HomeworkSubmission submission) {
        Long id = submission.getId();
        BigDecimal score = submission.getScore();
        String teacherComment = submission.getTeacherComment();
        homeworkSubmissionMapper.updateScore(id, score, teacherComment);
    }

    @Override
    public List<Homework> getHomeworkList(String courseId) {
        return homeworkMapper.selectHomeworkByCourseId(courseId);
    }

}