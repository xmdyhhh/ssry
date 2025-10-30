package com.ruoyi.ssry.mapper;

import java.util.List;
import com.ruoyi.ssry.domain.Student;
import org.apache.ibatis.annotations.Param;

/**
 * 学生信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-22
 */
public interface StudentMapper 
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
     * 删除学生信息
     * 
     * @param id 学生信息主键
     * @return 结果
     */
    public int deleteStudentById(String id);

    /**
     * 批量删除学生信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentByIds(String[] ids);

    Student selectStudentBystudentno(String loginName);

    List<Student> getStudentsByCollegeId(@Param("collegeIds") List<String> collegeIds);
}
