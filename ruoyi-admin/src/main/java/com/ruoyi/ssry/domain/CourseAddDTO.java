package com.ruoyi.ssry.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.ssry.domain.Course;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CourseAddDTO {

    @NotBlank(message = "课程编号不能为空")
    private String courseCode;

    @NotBlank(message = "课程名称不能为空")
    private String courseName;

    @NotNull(message = "学分不能为空")
    private BigDecimal credits;

    @NotBlank(message = "开课学期不能为空")
    private String semester;

    @NotNull(message = "最大选课人数不能为空")
    private Long maxEnrollment;

    private Long currentEnrollment = 0L;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "开课日期不能为空")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "结课日期不能为空")
    private Date endDate;

    @NotNull(message = "上课星期不能为空")
    private String dayOfWeek;

    @NotBlank(message = "开始时间不能为空")
    private String startTime; // HH:mm

    @NotBlank(message = "结束时间不能为空")
    private String endTime;

    @NotBlank(message = "教室不能为空")
    private String classroom;

    @NotBlank(message = "教师ID不能为空")
    private String teacherId;

    private Boolean collegeRestricted = false;
    private List<String> allowedCollegeIds;

}