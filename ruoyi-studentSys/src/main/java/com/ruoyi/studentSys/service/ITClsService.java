package com.ruoyi.studentSys.service;

import java.util.List;
import com.ruoyi.studentSys.domain.TCls;

/**
 * 班级信息Service接口
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
public interface ITClsService 
{
    /**
     * 查询班级信息
     * 
     * @param clsId 班级信息主键
     * @return 班级信息
     */
    public TCls selectTClsByClsId(String clsId);

    /**
     * 查询班级信息列表
     * 
     * @param tCls 班级信息
     * @return 班级信息集合
     */
    public List<TCls> selectTClsList(TCls tCls);

    /**
     * 新增班级信息
     * 
     * @param tCls 班级信息
     * @return 结果
     */
    public int insertTCls(TCls tCls);

    /**
     * 修改班级信息
     * 
     * @param tCls 班级信息
     * @return 结果
     */
    public int updateTCls(TCls tCls);

    /**
     * 批量删除班级信息
     * 
     * @param clsIds 需要删除的班级信息主键集合
     * @return 结果
     */
    public int deleteTClsByClsIds(String clsIds);

    /**
     * 删除班级信息信息
     * 
     * @param clsId 班级信息主键
     * @return 结果
     */
    public int deleteTClsByClsId(String clsId);
}
