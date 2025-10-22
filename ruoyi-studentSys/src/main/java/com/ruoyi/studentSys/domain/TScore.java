package com.ruoyi.studentSys.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成绩信息对象 t_score
 * 
 * @author ruoyi
 * @date 2025-09-29
 */
public class TScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 学号 */
    @Excel(name = "学号")
    private String stuId;

    /** 课程id */
    @Excel(name = "课程id")
    private String courseId;

    /** 分数 */
    @Excel(name = "分数")
    private BigDecimal stuScore;

    /** 考核状态（1正常，2缺考，3缓考） */
    @Excel(name = "考核状态", readConverterExp = "1=正常，2缺考，3缓考")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setStuId(String stuId) 
    {
        this.stuId = stuId;
    }

    public String getStuId() 
    {
        return stuId;
    }

    public void setCourseId(String courseId) 
    {
        this.courseId = courseId;
    }

    public String getCourseId() 
    {
        return courseId;
    }

    public void setStuScore(BigDecimal stuScore) 
    {
        this.stuScore = stuScore;
    }

    public BigDecimal getStuScore() 
    {
        return stuScore;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("stuId", getStuId())
            .append("courseId", getCourseId())
            .append("stuScore", getStuScore())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
