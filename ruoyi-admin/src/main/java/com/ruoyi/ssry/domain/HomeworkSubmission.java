package com.ruoyi.ssry.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkSubmission {
    private Long id;
    private Long homeworkId;
    private Long studentId;
    private String studentName;
    private String studentNo;
    private String fileUrl;
    private String fileName;
    private LocalDateTime submitTime;
    private String status;            // submitted / late / revised
    private String teacherComment;
    private BigDecimal score;         // DECIMAL(5,2)
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}