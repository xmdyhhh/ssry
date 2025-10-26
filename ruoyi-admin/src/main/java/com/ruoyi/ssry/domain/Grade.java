package com.ruoyi.ssry.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Grade {
    private Long id;
    private String studentId;
    private String studentName; // 非数据库字段，用于显示
    private Long courseId;
    private String courseName;  // 非数据库字段
    private BigDecimal usualScore;
    private BigDecimal finalScore;
    private BigDecimal totalScore;
    private String semester;
    private Integer year;
    private Long gradedBy;
    private String teacherName; // 非数据库字段
    private Date createTime;
    private Date updateTime;
    private Double credits;

    public Grade() {
    }

    public Grade(Double  credits,Long id, String studentId, String studentName, Long courseId, String courseName, BigDecimal usualScore, BigDecimal finalScore, BigDecimal totalScore, String semester, Integer year, Long gradedBy, String teacherName, Date createTime, Date updateTime) {
        this.credits = credits;
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.usualScore = usualScore;
        this.finalScore = finalScore;
        this.totalScore = totalScore;
        this.semester = semester;
        this.year = year;
        this.gradedBy = gradedBy;
        this.teacherName = teacherName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getUsualScore() {
        return usualScore;
    }

    public void setUsualScore(BigDecimal usualScore) {
        this.usualScore = usualScore;
    }

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getGradedBy() {
        return gradedBy;
    }

    public void setGradedBy(Long gradedBy) {
        this.gradedBy = gradedBy;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}