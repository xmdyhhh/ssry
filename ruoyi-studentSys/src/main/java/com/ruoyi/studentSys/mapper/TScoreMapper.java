package com.ruoyi.studentSys.mapper;

import java.util.List;
import com.ruoyi.studentSys.domain.TScore;
import org.apache.ibatis.annotations.Param;

/**
 * 成绩信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-29
 */
public interface TScoreMapper 
{
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
     * 删除成绩信息
     * 
     * @param id 成绩信息主键
     * @return 结果
     */
    public int deleteTScoreById(Long id);

    /**
     * 批量删除成绩信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTScoreByIds(String[] ids);

    public  Integer  selectRangeByCourseId (@Param("courseId") String courseId ,
                                            @Param("from")   Integer from ,
                                            @Param("to")   Integer to  );
    public  float  selectAvgByCourseId (@Param("courseId") String courseId ,
                                        @Param("clsId")   String clsId  );
}