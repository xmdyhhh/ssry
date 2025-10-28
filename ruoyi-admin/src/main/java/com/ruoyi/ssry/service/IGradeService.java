package com.ruoyi.ssry.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.ssry.domain.Grade;
import com.ruoyi.ssry.domain.TJInt;

import java.util.List;
import java.util.Map;

public interface IGradeService {
    public List<Grade> studentgradelist(String studentid);

    List<Grade> getcourselist(String courseId);

    Grade getGradeById(Long id);
    int updateGrade(Grade grade);

    List<TJInt> tsgradelist(String courseId);

    AjaxResult getzhubycourseid(String courseId);

    AjaxResult getCourseYearlyAvg(String courseId);
}
