package com.ruoyi.studentSys.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.studentSys.domain.TScore;
import com.ruoyi.studentSys.domain.TongJiInt;

/**
 * 成绩信息Service接口
 *
 * @author ruoyi
 * @date 2025-09-29
 */
public interface ITScoreService {
    /**
     * 查询成绩信息
     *
     * @param id 成绩信息主键
     * @return 成绩信息
     */
    public TScore selectTScoreById(Long id);

    /**
     * 查询成绩信息列表
     *
     * @param tScore 成绩信息
     * @return 成绩信息集合
     */
    public List<TScore> selectTScoreList(TScore tScore);

    /**
     * 新增成绩信息
     *
     * @param tScore 成绩信息
     * @return 结果
     */
    public int insertTScore(TScore tScore);

    /**
     * 修改成绩信息
     *
     * @param tScore 成绩信息
     * @return 结果
     */
    public int updateTScore(TScore tScore);

    /**
     * 批量删除成绩信息
     *
     * @param ids 需要删除的成绩信息主键集合
     * @return 结果
     */
    public int deleteTScoreByIds(String ids);

    /**
     * 删除成绩信息信息
     *
     * @param id 成绩信息主键
     * @return 结果
     */
    public int deleteTScoreById(Long id);

    public List<TongJiInt> selectRangeByCourseId(String courseId);

    public AjaxResult selectClsAvgByCourseId(String courseId);
}
