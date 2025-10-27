package com.ruoyi.ssry.service.impl;

import com.ruoyi.ssry.domain.Grade;
import com.ruoyi.ssry.mapper.GradeMapper;
import com.ruoyi.ssry.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IGradeServiceImpl implements IGradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> studentgradelist(String studentid) {
        return gradeMapper.studentgradelist(studentid);
    }

    @Override
    public List<Grade> getcourselist(String courseId) {
        return gradeMapper.getcourselist(courseId);
    }

    @Override
    public Grade getGradeById(Long id) {
        return gradeMapper.selectGradeById(id);
    }

    @Override
    public int updateGrade(Grade grade) {
        // 获取原始数据
        Grade old = gradeMapper.selectGradeById(grade.getId());
        if (old == null) {
            throw new RuntimeException("成绩记录不存在");
        }

        // 校验分数范围：0-100
        validateScore(grade.getUsualScore(), "平时成绩");
        validateScore(grade.getFinalScore(), "期末成绩");

        // 计算总评成绩（假设权重为40%和60%，可从课程表读取）
        BigDecimal usualWeight = BigDecimal.valueOf(40);
        BigDecimal finalWeight = BigDecimal.valueOf(60);

        BigDecimal usual = grade.getUsualScore() != null ? grade.getUsualScore() : old.getUsualScore();
        BigDecimal finals = grade.getFinalScore() != null ? grade.getFinalScore() : old.getFinalScore();

        BigDecimal total = usual.multiply(usualWeight)
                .add(finals.multiply(finalWeight))
                .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

        grade.setTotalScore(total);

        // 更新数据库
        return gradeMapper.updateGrade(grade);
    }

    private void validateScore(BigDecimal score, String name) {
        if (score != null &&
                (score.compareTo(BigDecimal.ZERO) < 0 || score.compareTo(BigDecimal.valueOf(100)) > 0)) {
            throw new RuntimeException(name + "必须在0-100之间");
        }
    }
}