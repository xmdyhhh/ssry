package com.ruoyi.ssry.service.impl;

import com.ruoyi.ssry.domain.CourseSettingVO;
import com.ruoyi.ssry.mapper.CourseSettingMapper;
import com.ruoyi.ssry.service.ICourseSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSettingServiceImpl implements ICourseSettingService {

    @Autowired
    private CourseSettingMapper courseSettingMapper;

    @Override
    public List<CourseSettingVO> list() {
        return courseSettingMapper.list();
    }

}
