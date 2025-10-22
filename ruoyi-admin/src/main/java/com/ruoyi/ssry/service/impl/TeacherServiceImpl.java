package com.ruoyi.ssry.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ssry.mapper.TeacherMapper;
import com.ruoyi.ssry.domain.Teacher;
import com.ruoyi.ssry.service.ITeacherService;
import com.ruoyi.common.core.text.Convert;

/**
 * 教师信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-22
 */
@Service
public class TeacherServiceImpl implements ITeacherService 
{
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 查询教师信息
     * 
     * @param id 教师信息主键
     * @return 教师信息
     */
    @Override
    public Teacher selectTeacherById(String id)
    {
        return teacherMapper.selectTeacherById(id);
    }

    /**
     * 查询教师信息列表
     * 
     * @param teacher 教师信息
     * @return 教师信息
     */
    @Override
    public List<Teacher> selectTeacherList(Teacher teacher)
    {
        return teacherMapper.selectTeacherList(teacher);
    }

    /**
     * 新增教师信息
     * 
     * @param teacher 教师信息
     * @return 结果
     */
    @Override
    public int insertTeacher(Teacher teacher)
    {
        teacher.setCreateTime(DateUtils.getNowDate());
        return teacherMapper.insertTeacher(teacher);
    }

    /**
     * 修改教师信息
     * 
     * @param teacher 教师信息
     * @return 结果
     */
    @Override
    public int updateTeacher(Teacher teacher)
    {
        teacher.setUpdateTime(DateUtils.getNowDate());
        return teacherMapper.updateTeacher(teacher);
    }

    /**
     * 批量删除教师信息
     * 
     * @param ids 需要删除的教师信息主键
     * @return 结果
     */
    @Override
    public int deleteTeacherByIds(String ids)
    {
        return teacherMapper.deleteTeacherByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除教师信息信息
     * 
     * @param id 教师信息主键
     * @return 结果
     */
    @Override
    public int deleteTeacherById(String id)
    {
        return teacherMapper.deleteTeacherById(id);
    }
}
