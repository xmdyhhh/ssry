package com.ruoyi.ssry.service;

import com.ruoyi.ssry.domain.Homework;
import com.ruoyi.ssry.domain.HomeworkSubmission;


import java.util.List;


public interface IHomeworkSubmissionService {

    List<HomeworkSubmission> getSubmissionsByhomeworkId(String homeworkId);

    void updateScore(HomeworkSubmission submission);

    List<Homework> getHomeworkList(String courseId);

}
