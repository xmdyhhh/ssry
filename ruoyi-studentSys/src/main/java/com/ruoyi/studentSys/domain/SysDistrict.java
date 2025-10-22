package com.ruoyi.studentSys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地区信息对象 sys_district
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class SysDistrict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地区编号 */
    private String districtId;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String districtName;

    /**  */
    @Excel(name = "")
    private String districtFullname;

    /**  */
    @Excel(name = "")
    private String pinyin;

    /**  */
    @Excel(name = "")
    private String lat;

    /**  */
    @Excel(name = "")
    private String lng;

    /**  */
    @Excel(name = "")
    private String districtType;

    /**  */
    @Excel(name = "")
    private String parentId;

    /**  */
    @Excel(name = "")
    private String status;

    public void setDistrictId(String districtId) 
    {
        this.districtId = districtId;
    }

    public String getDistrictId() 
    {
        return districtId;
    }

    public void setDistrictName(String districtName) 
    {
        this.districtName = districtName;
    }

    public String getDistrictName() 
    {
        return districtName;
    }

    public void setDistrictFullname(String districtFullname) 
    {
        this.districtFullname = districtFullname;
    }

    public String getDistrictFullname() 
    {
        return districtFullname;
    }

    public void setPinyin(String pinyin) 
    {
        this.pinyin = pinyin;
    }

    public String getPinyin() 
    {
        return pinyin;
    }

    public void setLat(String lat) 
    {
        this.lat = lat;
    }

    public String getLat() 
    {
        return lat;
    }

    public void setLng(String lng) 
    {
        this.lng = lng;
    }

    public String getLng() 
    {
        return lng;
    }

    public void setDistrictType(String districtType) 
    {
        this.districtType = districtType;
    }

    public String getDistrictType() 
    {
        return districtType;
    }

    public void setParentId(String parentId) 
    {
        this.parentId = parentId;
    }

    public String getParentId() 
    {
        return parentId;
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
            .append("districtId", getDistrictId())
            .append("districtName", getDistrictName())
            .append("districtFullname", getDistrictFullname())
            .append("pinyin", getPinyin())
            .append("lat", getLat())
            .append("lng", getLng())
            .append("districtType", getDistrictType())
            .append("parentId", getParentId())
            .append("status", getStatus())
            .append("remark", getRemark())
            .toString();
    }
}
