package com.ruoyi.ssry.service;

import com.ruoyi.ssry.domain.CourseSettingVO;

import java.util.List;

public interface ICourseSettingService {
    List<CourseSettingVO> list();

    /**
     * 为多个课程设置允许选课的学院
     * @param courseIds 课程ID数组
     * @param collegeIds 学院ID数组，null 或 空数组 表示“全校可选”
     */
    void saveAllowedColleges(Long[] courseIds, Long[] collegeIds);
}
