package com.ruoyi.studentSys.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.CxSelect;
import com.ruoyi.common.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentSys.mapper.SysDistrictMapper;
import com.ruoyi.studentSys.domain.SysDistrict;
import com.ruoyi.studentSys.service.ISysDistrictService;
import com.ruoyi.common.core.text.Convert;

/**
 * 地区信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class SysDistrictServiceImpl implements ISysDistrictService 
{
    @Autowired
    private SysDistrictMapper sysDistrictMapper;

    /**
     * 查询地区信息
     * 
     * @param districtId 地区信息主键
     * @return 地区信息
     */
    @Override
    public SysDistrict selectSysDistrictByDistrictId(String districtId)
    {
        return sysDistrictMapper.selectSysDistrictByDistrictId(districtId);
    }

    /**
     * 查询地区信息列表
     * 
     * @param sysDistrict 地区信息
     * @return 地区信息
     */
    @Override
    public List<SysDistrict> selectSysDistrictList(SysDistrict sysDistrict)
    {
        return sysDistrictMapper.selectSysDistrictList(sysDistrict);
    }

    /**
     * 新增地区信息
     * 
     * @param sysDistrict 地区信息
     * @return 结果
     */
    @Override
    public int insertSysDistrict(SysDistrict sysDistrict)
    {
        return sysDistrictMapper.insertSysDistrict(sysDistrict);
    }

    /**
     * 修改地区信息
     * 
     * @param sysDistrict 地区信息
     * @return 结果
     */
    @Override
    public int updateSysDistrict(SysDistrict sysDistrict)
    {
        return sysDistrictMapper.updateSysDistrict(sysDistrict);
    }

    /**
     * 批量删除地区信息
     * 
     * @param districtIds 需要删除的地区信息主键
     * @return 结果
     */
    @Override
    public int deleteSysDistrictByDistrictIds(String districtIds)
    {
        return sysDistrictMapper.deleteSysDistrictByDistrictIds(Convert.toStrArray(districtIds));
    }

    /**
     * 删除地区信息信息
     * 
     * @param districtId 地区信息主键
     * @return 结果
     */
    @Override
    public int deleteSysDistrictByDistrictId(String districtId)
    {
        return sysDistrictMapper.deleteSysDistrictByDistrictId(districtId);
    }


    //第一种方式，多次读数据库，效率低下，使用缓存后提高了访问速度
    public List<CxSelect> getCxSelectList2() {


        List<CxSelect> templist =(List<CxSelect>) CacheUtils.get("district");
        if(templist!=null)
            return templist;


        List<CxSelect> rootList = new ArrayList<>();
        SysDistrict vo = new SysDistrict();
        vo.setParentId("0");

        List<SysDistrict> pList = this.selectSysDistrictList(vo);
        for (SysDistrict p : pList) {

            CxSelect pCxSelect =   new CxSelect(p.getDistrictId(), p.getDistrictName());
            rootList.add(pCxSelect);
            List<CxSelect> cityList = new ArrayList<>();

            vo.setParentId(p.getDistrictId());
            List<SysDistrict> cList = this.selectSysDistrictList(vo);
            for (SysDistrict c : cList)
            {
                CxSelect cCxSelect = new CxSelect(c.getDistrictId(), c.getDistrictName());
                cityList.add(cCxSelect);

                vo.setParentId(c.getDistrictId());
                List<SysDistrict> dList = this.selectSysDistrictList(vo);
                List<CxSelect> disList = new ArrayList<>();

                for (SysDistrict sysDistrict : dList) {
                    CxSelect disCxSelect = new CxSelect(sysDistrict.getDistrictId(), sysDistrict.getDistrictName());
                    disList.add(disCxSelect);
                }
                cCxSelect.setS(disList);
            }
            pCxSelect.setS(cityList);
        }

        CacheUtils.put("district",rootList);

        return rootList;
    }

    //第二种方式 一次从数据库中取出，然后在内存中组织数据，效率比多次查询数据库要高很多，再结合缓存进一步提高效率
    @Override
    public List<CxSelect> getCxSelectList() {
        // 一次性加载所有地区数据
        List<SysDistrict> allDistricts = this.selectSysDistrictList(new SysDistrict());

        // 构建父ID到子地区列表的映射，提高查询效率
        Map<String, List<SysDistrict>> districtMap = allDistricts.stream()
                .collect(Collectors.groupingBy(SysDistrict::getParentId));

        // 从根节点(ParentId为"0")开始构建层级结构
        return buildCxSelectTree("0", districtMap);
    }

    /**
     * 递归构建地区选择树
     * @param parentId 父级ID
     * @param districtMap 父ID到子地区列表的映射
     * @return 地区选择列表
     */
    private List<CxSelect> buildCxSelectTree(String parentId, Map<String, List<SysDistrict>> districtMap) {
        // 获取当前父ID对应的所有子地区，若没有则返回空列表
        List<SysDistrict> children = districtMap.getOrDefault(parentId, Collections.emptyList());

        // 转换为CxSelect并递归设置子节点
        return children.stream()
                .map(district -> {
                    CxSelect cxSelect = new CxSelect(district.getDistrictId(), district.getDistrictName());
                    // 递归构建子节点
                    cxSelect.setS(buildCxSelectTree(district.getDistrictId(), districtMap));
                    return cxSelect;
                })
                .collect(Collectors.toList());
    }
}