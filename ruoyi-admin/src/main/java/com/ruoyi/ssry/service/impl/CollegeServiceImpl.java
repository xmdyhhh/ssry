package com.ruoyi.ssry.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ssry.mapper.CollegeMapper;
import com.ruoyi.ssry.domain.College;
import com.ruoyi.ssry.service.ICollegeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学院信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-22
 */
@Service
public class CollegeServiceImpl implements ICollegeService 
{
    @Autowired
    private CollegeMapper collegeMapper;

    /**
     * 查询学院信息
     * 
     * @param id 学院信息主键
     * @return 学院信息
     */
    @Override
    public College selectCollegeById(String id)
    {
        return collegeMapper.selectCollegeById(id);
    }

    /**
     * 查询学院信息列表
     * 
     * @param college 学院信息
     * @return 学院信息
     */
    @Override
    public List<College> selectCollegeList(College college)
    {
        return collegeMapper.selectCollegeList(college);
    }

    /**
     * 新增学院信息
     * 
     * @param college 学院信息
     * @return 结果
     */
    @Override
    public int insertCollege(College college)
    {
        college.setCreateTime(DateUtils.getNowDate());
        return collegeMapper.insertCollege(college);
    }

    /**
     * 修改学院信息
     * 
     * @param college 学院信息
     * @return 结果
     */
    @Override
    public int updateCollege(College college)
    {
        college.setUpdateTime(DateUtils.getNowDate());
        return collegeMapper.updateCollege(college);
    }

    /**
     * 批量删除学院信息
     * 
     * @param ids 需要删除的学院信息主键
     * @return 结果
     */
    @Override
    public int deleteCollegeByIds(String ids)
    {
        return collegeMapper.deleteCollegeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学院信息信息
     * 
     * @param id 学院信息主键
     * @return 结果
     */
    @Override
    public int deleteCollegeById(String id)
    {
        return collegeMapper.deleteCollegeById(id);
    }

}
