package com.ruoyi.ssry.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 课程设置展示对象（VO）
 * 用于前端“课程管理”页面展示：课程信息 + 教师姓名 + 可选学院等
 */
public class CourseSettingVO {

    /** 主键ID */
    private String id;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private String courseCode;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 教师姓名（非数据库字段） */
    @Excel(name = "授课教师")
    private String teacherName;

    /** 开课日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "开课日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结课日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "结课日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 开始时间（String 类型，对应 TIME 字段） */
    @JsonProperty("startTime")
    @Excel(name = "开始时间", width = 20)
    private String startTime;

    /** 结束时间（String 类型，对应 TIME 字段） */
    @JsonProperty("endTime")
    @Excel(name = "结束时间", width = 20)
    private String endTime;

    /** 学期 */
    @JsonProperty("semester")
    @Excel(name = "学期", width = 20)
    private String semester;

    /**
     * 可选学院（字符串形式，逗号分隔）
     */
    @Excel(name = "可选学院", readConverterExp = "格式=：学院A,学院B")
    private String allowedColleges;

    // getter & setter
    public String getAllowedColleges() {
        return allowedColleges;
    }

    public void setAllowedColleges(String allowedColleges) {
        this.allowedColleges = allowedColleges;
    }

    // --------------------- Getter & Setter ---------------------

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    // --------------------- toString ---------------------

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("courseCode", getCourseCode())
                .append("courseName", getCourseName())
                .append("teacherName", getTeacherName())
                .append("startDate", getStartDate())
                .append("endDate", getEndDate())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("allowedColleges", getAllowedColleges())
                .toString();
    }
}