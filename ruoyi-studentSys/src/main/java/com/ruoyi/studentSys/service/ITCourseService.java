package com.ruoyi.studentSys.service;

import java.util.List;
import com.ruoyi.studentSys.domain.TCourse;

/**
 * 课程信息Service接口
 * 
 * @author ruoyi
 * @date 2025-09-29
 */
public interface ITCourseService 
{
    /**
     * 查询课程信息
     * 
     * @param courseId 课程信息主键
     * @return 课程信息
     */
    public TCourse selectTCourseByCourseId(String courseId);

    /**
     * 查询课程信息列表
     * 
     * @param tCourse 课程信息
     * @return 课程信息集合
     */
    public List<TCourse> selectTCourseList(TCourse tCourse);

    /**
     * 新增课程信息
     * 
     * @param tCourse 课程信息
     * @return 结果
     */
    public int insertTCourse(TCourse tCourse);

    /**
     * 修改课程信息
     * 
     * @param tCourse 课程信息
     * @return 结果
     */
    public int updateTCourse(TCourse tCourse);

    /**
     * 批量删除课程信息
     * 
     * @param courseIds 需要删除的课程信息主键集合
     * @return 结果
     */
    public int deleteTCourseByCourseIds(String courseIds);

    /**
     * 删除课程信息信息
     * 
     * @param courseId 课程信息主键
     * @return 结果
     */
    public int deleteTCourseByCourseId(String courseId);
}
