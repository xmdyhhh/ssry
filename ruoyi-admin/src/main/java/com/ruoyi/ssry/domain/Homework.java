package com.ruoyi.ssry.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Homework {
    private Long id;
    private String title;
    private String content;
    private Long courseId;
    private LocalDate issueDate;      // 发布日期
    private LocalDate dueDate;        // 截止日期
    private LocalTime dueTime;        // 截止时间
    private String requiredFormat;    // 如 "pdf,doc"
    private Integer maxSizeMb;        // 默认 10
    private Long createdBy;           // 教师 ID
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 非数据库字段
    private String courseName;
}