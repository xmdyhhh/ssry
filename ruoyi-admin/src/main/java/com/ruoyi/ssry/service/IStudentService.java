package com.ruoyi.ssry.service;

import java.util.List;
import com.ruoyi.ssry.domain.Student;

/**
 * 学生信息Service接口
 * 
 * @author ruoyi
 * @date 2025-10-22
 */
public interface IStudentService 
{
    /**
     * 查询学生信息
     * 
     * @param id 学生信息主键
     * @return 学生信息
     */
    public Student selectStudentById(String id);

    /**
     * 查询学生信息列表
     * 
     * @param student 学生信息
     * @return 学生信息集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学生信息
     * 
     * @param student 学生信息
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生信息
     * 
     * @param student 学生信息
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 批量删除学生信息
     * 
     * @param ids 需要删除的学生信息主键集合
     * @return 结果
     */
    public int deleteStudentByIds(String ids);

    Student selectStudentBystudentno(String loginName);

    List<Student> selectStudentsByCollegeId(Long receiverId);

    List<Student> selectStudentByCourseId(Long courseId);
}
