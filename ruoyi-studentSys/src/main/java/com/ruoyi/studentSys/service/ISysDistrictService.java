package com.ruoyi.studentSys.service;

import java.util.List;

import com.ruoyi.common.core.domain.CxSelect;
import com.ruoyi.studentSys.domain.SysDistrict;

/**
 * 地区信息Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface ISysDistrictService 
{
    /**
     * 查询地区信息
     * 
     * @param districtId 地区信息主键
     * @return 地区信息
     */
    public SysDistrict selectSysDistrictByDistrictId(String districtId);

    /**
     * 查询地区信息列表
     * 
     * @param sysDistrict 地区信息
     * @return 地区信息集合
     */
    public List<SysDistrict> selectSysDistrictList(SysDistrict sysDistrict);

    /**
     * 新增地区信息
     * 
     * @param sysDistrict 地区信息
     * @return 结果
     */
    public int insertSysDistrict(SysDistrict sysDistrict);

    /**
     * 修改地区信息
     * 
     * @param sysDistrict 地区信息
     * @return 结果
     */
    public int updateSysDistrict(SysDistrict sysDistrict);

    /**
     * 批量删除地区信息
     * 
     * @param districtIds 需要删除的地区信息主键集合
     * @return 结果
     */
    public int deleteSysDistrictByDistrictIds(String districtIds);

    /**
     * 删除地区信息信息
     * 
     * @param districtId 地区信息主键
     * @return 结果
     */
    public int deleteSysDistrictByDistrictId(String districtId);

    public List<CxSelect> getCxSelectList();
}
