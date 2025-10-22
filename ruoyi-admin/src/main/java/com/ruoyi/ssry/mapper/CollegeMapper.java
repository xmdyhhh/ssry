package com.ruoyi.ssry.mapper;

import java.util.List;
import com.ruoyi.ssry.domain.College;

/**
 * 学院信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-22
 */
public interface CollegeMapper 
{
    /**
     * 查询学院信息
     * 
     * @param id 学院信息主键
     * @return 学院信息
     */
    public College selectCollegeById(String id);

    /**
     * 查询学院信息列表
     * 
     * @param college 学院信息
     * @return 学院信息集合
     */
    public List<College> selectCollegeList(College college);

    /**
     * 新增学院信息
     * 
     * @param college 学院信息
     * @return 结果
     */
    public int insertCollege(College college);

    /**
     * 修改学院信息
     * 
     * @param college 学院信息
     * @return 结果
     */
    public int updateCollege(College college);

    /**
     * 删除学院信息
     * 
     * @param id 学院信息主键
     * @return 结果
     */
    public int deleteCollegeById(String id);

    /**
     * 批量删除学院信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCollegeByIds(String[] ids);
}
