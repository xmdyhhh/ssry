package com.ruoyi.ssry.mapper;

import com.ruoyi.ssry.domain.HomeworkSubmission;
import com.ruoyi.ssry.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface HomeworkSubmissionMapper {

    List<HomeworkSubmission> getSubmissionsByhomeworkId(String courseId);

    void updateScore(@Param("id") Long id, @Param("score") BigDecimal score, @Param("teacherComment") String teaacherComment);

    Long countSubmittedByHomeworkId(Long homeworkId);

    void addnullSubmission(@Param("homeworkId") Long homeworkId,@Param("studentId") Long studentId);

    void deleteByHomeworkId(Long id);

    List<Student> getStudentListByHomeworkId(String homeworkId);
}
