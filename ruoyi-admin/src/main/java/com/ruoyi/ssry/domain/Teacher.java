package com.ruoyi.ssry.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教师信息对象 teacher
 * 
 * @author ruoyi
 * @date 2025-10-22
 */
public class Teacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 教师工号（登录账号） */
    @Excel(name = "教师工号", readConverterExp = "登=录账号")
    private String teacherNo;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别（0:男, 1:女） */
    @Excel(name = "性别", readConverterExp = "0=:男,,1=:女")
    private String sex;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 职称（如：教授、副教授） */
    @Excel(name = "职称", readConverterExp = "如=：教授、副教授")
    private String title;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hireDate;

    /** 所属学院ID */
    @Excel(name = "所属学院ID")
    private String collegeId;

    /** 办公室位置 */
    @Excel(name = "办公室位置")
    private String office;

    /** 学位（如：博士、硕士） */
    @Excel(name = "学位", readConverterExp = "如=：博士、硕士")
    private String degree;

    /** 状态（0:正常, 1:停用） */
    @Excel(name = "状态", readConverterExp = "0=:正常,,1=:停用")
    private String status;

    /** 登录密码（BCrypt加密） */
    @Excel(name = "登录密码", readConverterExp = "B=Crypt加密")
    private String password;

    /** 头像图片路径 */
    @Excel(name = "头像图片路径")
    private String avatar;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public void setTeacherNo(String teacherNo) 
    {
        this.teacherNo = teacherNo;
    }

    public String getTeacherNo() 
    {
        return teacherNo;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }

    public void setBirthDate(Date birthDate) 
    {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() 
    {
        return birthDate;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setHireDate(Date hireDate) 
    {
        this.hireDate = hireDate;
    }

    public Date getHireDate() 
    {
        return hireDate;
    }

    public void setCollegeId(String collegeId) 
    {
        this.collegeId = collegeId;
    }

    public String getCollegeId() 
    {
        return collegeId;
    }

    public void setOffice(String office) 
    {
        this.office = office;
    }

    public String getOffice() 
    {
        return office;
    }

    public void setDegree(String degree) 
    {
        this.degree = degree;
    }

    public String getDegree() 
    {
        return degree;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("teacherNo", getTeacherNo())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthDate", getBirthDate())
            .append("title", getTitle())
            .append("hireDate", getHireDate())
            .append("collegeId", getCollegeId())
            .append("office", getOffice())
            .append("degree", getDegree())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("password", getPassword())
            .append("avatar", getAvatar())
            .toString();
    }
}
