package com.ruoyi.studentSys.service;

import java.util.List;

import com.ruoyi.studentSys.domain.TCls;
import com.ruoyi.studentSys.domain.TStudent;
import com.ruoyi.studentSys.domain.TongJiInt;

/**
 * 学生信息Service接口
 * 
 * @author ruoyi
 * @date 2025-09-22
 */
public interface ITStudentService 
{
    /**
     * 查询学生信息
     * 
     * @param stuId 学生信息主键
     * @return 学生信息
     */
    public TStudent selectTStudentByStuId(String stuId);

    /**
     * 查询学生信息列表
     * 
     * @param tStudent 学生信息
     * @return 学生信息集合
     */
    public List<TStudent> selectTStudentList(TStudent tStudent);

    /**
     * 新增学生信息
     * 
     * @param tStudent 学生信息
     * @return 结果
     */
    public int insertTStudent(TStudent tStudent);

    /**
     * 修改学生信息
     * 
     * @param tStudent 学生信息
     * @return 结果
     */
    public int updateTStudent(TStudent tStudent);

    /**
     * 批量删除学生信息
     * 
     * @param stuIds 需要删除的学生信息主键集合
     * @return 结果
     */
    public int deleteTStudentByStuIds(String stuIds);

    /**
     * 删除学生信息信息
     * 
     * @param stuId 学生信息主键
     * @return 结果
     */
    public int deleteTStudentByStuId(String stuId);

    List<TongJiInt> selectCountByProvince();
}
