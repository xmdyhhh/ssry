package com.ruoyi.ssry.mapper;

import java.util.List;

import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.domain.CourseAddDTO;
import com.ruoyi.ssry.domain.CourseAllowedCollege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 课程信息Mapper接口
 *
 * @author ruoyi
 * @date 2025-10-22
 */
@Mapper
public interface CourseMapper {

    /**
     * 查询课程信息
     *
     * @param id 课程信息主键
     * @return 课程信息
     */
    Course selectCourseById(String id);

    /**
     * 查询课程信息列表
     *
     * @param course 课程信息
     * @return 课程信息集合
     */
    List<Course> selectCourseList(Course course);

    /**
     * 新增课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    int insertCourse(Course course);

    /**
     * 修改课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    int updateCourse(Course course);

    /**
     * 删除课程信息
     *
     * @param id 课程信息主键
     * @return 结果
     */
    int deleteCourseById(String id);

    /**
     * 批量删除课程信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCourseByIds(String[] ids);

    /**
     * 批量插入课程允许的学院（白名单）
     *
     * @param relations 课程与学院的关联关系列表
     * @return 影响的行数
     */
    int batchInsertAllowedColleges(@Param("list") List<CourseAllowedCollege> relations);

    List<Course> selectenrolledCourseBystudentId(String studentId);

    List<Course> selectCourseByteacherId(String id);
}