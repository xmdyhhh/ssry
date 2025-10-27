package com.ruoyi.ssry.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程信息对象 course
 */
public class Course extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // -------------------------------
    // 新增字段：教师姓名（非数据库字段）
    // -------------------------------
    @JsonProperty("teacherName")  // 确保 JSON 输出为 camelCase
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /** 主键ID */
    private String id;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private String courseCode;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 学分 */
    @Excel(name = "学分")
    private BigDecimal credits;

    /** 开课学期（如：秋季、春季） */
    @Excel(name = "开课学期", readConverterExp = "如=：秋季、春季")
    private String semester;

    /** 最大选课人数 */
    @Excel(name = "最大选课人数")
    private Long maxEnrollment;

    /** 当前选课人数 */
    @Excel(name = "当前选课人数")
    private Long currentEnrollment;

    /** 开课日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "开课日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结课日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "结课日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 上课星期（Mon, Tue等） */
    @Excel(name = "上课星期", readConverterExp = "M=on,,T=ue等")
    private String dayOfWeek;

    /**
     * 开始时间（数据库为 TIME 类型）
     * 使用 String 避免 Date 显示为 1970-01-01
     */
    @JsonProperty("startTime")
    private String startTime;

    /**
     * 结束时间（数据库为 TIME 类型）
     * 使用 String 避免 Date 显示为 1970-01-01
     */
    @JsonProperty("endTime")
    private String endTime;

    /** 教室 */
    @Excel(name = "教室")
    private String classroom;

    /** 授课教师ID */
    @Excel(name = "授课教师ID")
    private String teacherId;

    // -------------------- Getter & Setter --------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getCredits() {
        return credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Long getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(Long maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public Long getCurrentEnrollment() {
        return currentEnrollment;
    }

    public void setCurrentEnrollment(Long currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    // 使用 String 接收 TIME 类型
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("courseCode", getCourseCode())
                .append("courseName", getCourseName())
                .append("credits", getCredits())
                .append("semester", getSemester())
                .append("maxEnrollment", getMaxEnrollment())
                .append("currentEnrollment", getCurrentEnrollment())
                .append("startDate", getStartDate())
                .append("endDate", getEndDate())
                .append("dayOfWeek", getDayOfWeek())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("classroom", getClassroom())
                .append("teacherId", getTeacherId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}