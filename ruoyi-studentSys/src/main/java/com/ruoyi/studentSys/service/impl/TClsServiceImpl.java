package com.ruoyi.studentSys.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentSys.mapper.TClsMapper;
import com.ruoyi.studentSys.domain.TCls;
import com.ruoyi.studentSys.service.ITClsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 班级信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
@Service
public class TClsServiceImpl implements ITClsService 
{
    @Autowired
    private TClsMapper tClsMapper;

    /**
     * 查询班级信息
     * 
     * @param clsId 班级信息主键
     * @return 班级信息
     */
    @Override
    public TCls selectTClsByClsId(String clsId)
    {
        return tClsMapper.selectTClsByClsId(clsId);
    }

    /**
     * 查询班级信息列表
     * 
     * @param tCls 班级信息
     * @return 班级信息
     */
    @Override
    public List<TCls> selectTClsList(TCls tCls)
    {
        return tClsMapper.selectTClsList(tCls);
    }

    /**
     * 新增班级信息
     * 
     * @param tCls 班级信息
     * @return 结果
     */
    @Override
    public int insertTCls(TCls tCls)
    {
        tCls.setCreateTime(DateUtils.getNowDate());
        return tClsMapper.insertTCls(tCls);
    }

    /**
     * 修改班级信息
     * 
     * @param tCls 班级信息
     * @return 结果
     */
    @Override
    public int updateTCls(TCls tCls)
    {
        tCls.setUpdateTime(DateUtils.getNowDate());
        return tClsMapper.updateTCls(tCls);
    }

    /**
     * 批量删除班级信息
     * 
     * @param clsIds 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public int deleteTClsByClsIds(String clsIds)
    {
        return tClsMapper.deleteTClsByClsIds(Convert.toStrArray(clsIds));
    }

    /**
     * 删除班级信息信息
     * 
     * @param clsId 班级信息主键
     * @return 结果
     */
    @Override
    public int deleteTClsByClsId(String clsId)
    {
        return tClsMapper.deleteTClsByClsId(clsId);
    }
}
