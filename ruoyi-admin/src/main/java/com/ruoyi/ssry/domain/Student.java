package com.ruoyi.ssry.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生信息对象 student
 * 
 * @author ruoyi
 * @date 2025-10-22
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 学号（登录账号） */
    @Excel(name = "学号", readConverterExp = "登=录账号")
    private String studentNo;

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

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phonenumber;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 年级（如：大一、大二） */
    @Excel(name = "年级", readConverterExp = "如=：大一、大二")
    private String grade;

    /** 所属学院ID */
    @Excel(name = "所属学院ID")
    private String collegeId;

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

    public void setStudentNo(String studentNo) 
    {
        this.studentNo = studentNo;
    }

    public String getStudentNo() 
    {
        return studentNo;
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

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }

    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }

    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }

    public void setCollegeId(String collegeId) 
    {
        this.collegeId = collegeId;
    }

    public String getCollegeId() 
    {
        return collegeId;
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
            .append("studentNo", getStudentNo())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthDate", getBirthDate())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("major", getMajor())
            .append("grade", getGrade())
            .append("collegeId", getCollegeId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("password", getPassword())
            .append("avatar", getAvatar())
            .toString();
    }
}
