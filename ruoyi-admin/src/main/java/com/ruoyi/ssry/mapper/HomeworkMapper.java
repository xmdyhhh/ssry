package com.ruoyi.ssry.mapper;

import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.domain.Homework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeworkMapper {


    List<Homework> selectHomeworkByCourseIds(@Param("courseIds") List<String> courseIds);

    void addHomework(Homework  homework);

    List<Homework> selectHomeworkByCourseId(String courseId);

    int deleteHomeworkById(Long id);

    Long getCourseIdByHomeworkId(Long homeworkId);

    Homework selectHomeworkById(Long homeworkId);
}
