package com.ruoyi.studentSys.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程信息对象 t_course
 * 
 * @author ruoyi
 * @date 2025-09-29
 */
public class TCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    private String courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 学分 */
    @Excel(name = "学分")
    private BigDecimal courseCredit;

    /** 开课学期 */
    @Excel(name = "开课学期")
    private String courseTerm;

    public void setCourseId(String courseId) 
    {
        this.courseId = courseId;
    }

    public String getCourseId() 
    {
        return courseId;
    }

    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }

    public void setCourseCredit(BigDecimal courseCredit) 
    {
        this.courseCredit = courseCredit;
    }

    public BigDecimal getCourseCredit() 
    {
        return courseCredit;
    }

    public void setCourseTerm(String courseTerm) 
    {
        this.courseTerm = courseTerm;
    }

    public String getCourseTerm() 
    {
        return courseTerm;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("courseCredit", getCourseCredit())
            .append("courseTerm", getCourseTerm())
            .append("remark", getRemark())
            .toString();
    }
}
