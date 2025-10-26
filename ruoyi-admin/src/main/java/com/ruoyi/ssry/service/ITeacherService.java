package com.ruoyi.ssry.service;

import java.util.List;

import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.domain.Teacher;

/**
 * 教师信息Service接口
 * 
 * @author ruoyi
 * @date 2025-10-22
 */
public interface ITeacherService 
{
    /**
     * 查询教师信息
     * 
     * @param id 教师信息主键
     * @return 教师信息
     */
    public Teacher selectTeacherById(String id);

    /**
     * 查询教师信息列表
     * 
     * @param teacher 教师信息
     * @return 教师信息集合
     */
    public List<Teacher> selectTeacherList(Teacher teacher);

    /**
     * 新增教师信息
     * 
     * @param teacher 教师信息
     * @return 结果
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 修改教师信息
     * 
     * @param teacher 教师信息
     * @return 结果
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 批量删除教师信息
     * 
     * @param ids 需要删除的教师信息主键集合
     * @return 结果
     */
    public int deleteTeacherByIds(String ids);

    /**
     * 删除教师信息信息
     * 
     * @param id 教师信息主键
     * @return 结果
     */
    public int deleteTeacherById(String id);

    Teacher selectTeacherByteacherno(String loginName);

    List<Course> getcourselist(String id);
}
