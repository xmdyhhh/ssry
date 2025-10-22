package com.ruoyi.studentSys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级信息对象 t_cls
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
public class TCls extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班级编号 */
    private String clsId;

    /** 专业编号(来自字典表中的专业) */
    @Excel(name = "专业编号(来自字典表中的专业)")
    private String majorId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String clsName;

    public void setClsId(String clsId) 
    {
        this.clsId = clsId;
    }

    public String getClsId() 
    {
        return clsId;
    }

    public void setMajorId(String majorId) 
    {
        this.majorId = majorId;
    }

    public String getMajorId() 
    {
        return majorId;
    }

    public void setClsName(String clsName) 
    {
        this.clsName = clsName;
    }

    public String getClsName() 
    {
        return clsName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clsId", getClsId())
            .append("majorId", getMajorId())
            .append("clsName", getClsName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
