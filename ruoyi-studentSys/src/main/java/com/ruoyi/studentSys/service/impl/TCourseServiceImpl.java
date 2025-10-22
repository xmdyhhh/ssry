package com.ruoyi.studentSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentSys.mapper.TCourseMapper;
import com.ruoyi.studentSys.domain.TCourse;
import com.ruoyi.studentSys.service.ITCourseService;
import com.ruoyi.common.core.text.Convert;

/**
 * 课程信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-29
 */
@Service
public class TCourseServiceImpl implements ITCourseService 
{
    @Autowired
    private TCourseMapper tCourseMapper;

    /**
     * 查询课程信息
     * 
     * @param courseId 课程信息主键
     * @return 课程信息
     */
    @Override
    public TCourse selectTCourseByCourseId(String courseId)
    {
        return tCourseMapper.selectTCourseByCourseId(courseId);
    }

    /**
     * 查询课程信息列表
     * 
     * @param tCourse 课程信息
     * @return 课程信息
     */
    @Override
    public List<TCourse> selectTCourseList(TCourse tCourse)
    {
        return tCourseMapper.selectTCourseList(tCourse);
    }

    /**
     * 新增课程信息
     * 
     * @param tCourse 课程信息
     * @return 结果
     */
    @Override
    public int insertTCourse(TCourse tCourse)
    {
        return tCourseMapper.insertTCourse(tCourse);
    }

    /**
     * 修改课程信息
     * 
     * @param tCourse 课程信息
     * @return 结果
     */
    @Override
    public int updateTCourse(TCourse tCourse)
    {
        return tCourseMapper.updateTCourse(tCourse);
    }

    /**
     * 批量删除课程信息
     * 
     * @param courseIds 需要删除的课程信息主键
     * @return 结果
     */
    @Override
    public int deleteTCourseByCourseIds(String courseIds)
    {
        return tCourseMapper.deleteTCourseByCourseIds(Convert.toStrArray(courseIds));
    }

    /**
     * 删除课程信息信息
     * 
     * @param courseId 课程信息主键
     * @return 结果
     */
    @Override
    public int deleteTCourseByCourseId(String courseId)
    {
        return tCourseMapper.deleteTCourseByCourseId(courseId);
    }
}
