package com.ruoyi.ssry.service.impl;

import com.ruoyi.ssry.domain.Grade;
import com.ruoyi.ssry.mapper.GradeMapper;
import com.ruoyi.ssry.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IGradeServiceImpl implements IGradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> studentgradelist(String studentid) {
        return gradeMapper.studentgradelist(studentid);
    }
}