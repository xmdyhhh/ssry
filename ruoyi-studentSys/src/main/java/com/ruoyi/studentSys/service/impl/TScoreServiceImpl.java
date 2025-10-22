package com.ruoyi.studentSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.studentSys.domain.TCls;
import com.ruoyi.studentSys.domain.TongJiInt;
import com.ruoyi.studentSys.service.ITClsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentSys.mapper.TScoreMapper;
import com.ruoyi.studentSys.domain.TScore;
import com.ruoyi.studentSys.service.ITScoreService;
import com.ruoyi.common.core.text.Convert;

/**
 * 成绩信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-29
 */
@Service
public class TScoreServiceImpl implements ITScoreService 
{
    @Autowired
    private TScoreMapper tScoreMapper;

    /**
     * 查询成绩信息
     * 
     * @param id 成绩信息主键
     * @return 成绩信息
     */
    @Override
    public TScore selectTScoreById(Long id)
    {
        return tScoreMapper.selectTScoreById(id);
    }

    /**
     * 查询成绩信息列表
     * 
     * @param tScore 成绩信息
     * @return 成绩信息
     */
    @Override
    public List<TScore> selectTScoreList(TScore tScore)
    {
        return tScoreMapper.selectTScoreList(tScore);
    }

    /**
     * 新增成绩信息
     * 
     * @param tScore 成绩信息
     * @return 结果
     */
    @Override
    public int insertTScore(TScore tScore)
    {
        tScore.setCreateTime(DateUtils.getNowDate());
        return tScoreMapper.insertTScore(tScore);
    }

    /**
     * 修改成绩信息
     * 
     * @param tScore 成绩信息
     * @return 结果
     */
    @Override
    public int updateTScore(TScore tScore)
    {
        tScore.setUpdateTime(DateUtils.getNowDate());
        return tScoreMapper.updateTScore(tScore);
    }

    /**
     * 批量删除成绩信息
     * 
     * @param ids 需要删除的成绩信息主键
     * @return 结果
     */
    @Override
    public int deleteTScoreByIds(String ids)
    {
        return tScoreMapper.deleteTScoreByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成绩信息信息
     * 
     * @param id 成绩信息主键
     * @return 结果
     */
    @Override
    public int deleteTScoreById(Long id)
    {
        return tScoreMapper.deleteTScoreById(id);
    }

    @Override
    public  List<TongJiInt>  selectRangeByCourseId(String courseId) {
        int count90 = tScoreMapper.selectRangeByCourseId(courseId,90,101);

        int count80 = tScoreMapper.selectRangeByCourseId(courseId,80,90);
        int count70 = tScoreMapper.selectRangeByCourseId(courseId,70,80);
        int count60 = tScoreMapper.selectRangeByCourseId(courseId,60,70);
        int count0 = tScoreMapper.selectRangeByCourseId(courseId,0,60);


        List<TongJiInt> list = new ArrayList<>();
        list.add(new TongJiInt("90以上",count90));
        list.add(new TongJiInt("80-90",count80));
        list.add(new TongJiInt("70-80",count70));
        list.add(new TongJiInt("60-70",count60));
        list.add(new TongJiInt("60以下",count0));

        return list;
    }

    @Autowired
    private ITClsService clsService;

    @Override
    public AjaxResult selectClsAvgByCourseId(String courseId)
    {
        List<TCls> clsList = clsService.selectTClsList(new TCls());
        ArrayList<String> clsNames = new ArrayList<>();
        ArrayList<Float> avgList = new ArrayList<>();

        for (TCls cls : clsList) {
            clsNames.add(cls.getClsName());
            float avg = tScoreMapper.selectAvgByCourseId(courseId, cls.getClsId());
            avgList.add(avg);
        }
        AjaxResult success = AjaxResult.success();

        success.put("clsNameList", clsNames);
        success.put("avgList",avgList );
        return success;

    }
}
