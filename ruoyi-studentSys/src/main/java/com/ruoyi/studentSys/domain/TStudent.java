package com.ruoyi.studentSys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生信息对象 t_student
 * 
 * @author ruoyi
 * @date 2025-09-22
 */
public class TStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学号 */
    private String stuId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String stuName;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 班级 */
    @Excel(name = "班级")
    private String clsId;

    /** 入学年份 */
    @Excel(name = "入学年份")
    private String entryYear;

    /** 性别（0男,1女,2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1女,2未知")
    private String sex;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mphone;

    /** 照片url */
    @Excel(name = "照片url")
    private String avatar;

    /** 学生状态（1正常 2休学3退学） */
    @Excel(name = "学生状态", readConverterExp = "1=正常,2=休学3退学")
    private String status;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区县 */
    @Excel(name = "区县")
    private String district;

    public void setStuId(String stuId) 
    {
        this.stuId = stuId;
    }

    public String getStuId() 
    {
        return stuId;
    }

    public void setStuName(String stuName) 
    {
        this.stuName = stuName;
    }

    public String getStuName() 
    {
        return stuName;
    }

    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }

    public void setClsId(String clsId) 
    {
        this.clsId = clsId;
    }

    public String getClsId() 
    {
        return clsId;
    }

    public void setEntryYear(String entryYear) 
    {
        this.entryYear = entryYear;
    }

    public String getEntryYear() 
    {
        return entryYear;
    }

    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setMphone(String mphone) 
    {
        this.mphone = mphone;
    }

    public String getMphone() 
    {
        return mphone;
    }

    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }

    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }

    public void setDistrict(String district) 
    {
        this.district = district;
    }

    public String getDistrict() 
    {
        return district;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuId", getStuId())
            .append("stuName", getStuName())
            .append("birthday", getBirthday())
            .append("clsId", getClsId())
            .append("entryYear", getEntryYear())
            .append("sex", getSex())
            .append("email", getEmail())
            .append("mphone", getMphone())
            .append("avatar", getAvatar())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .toString();
    }
}
