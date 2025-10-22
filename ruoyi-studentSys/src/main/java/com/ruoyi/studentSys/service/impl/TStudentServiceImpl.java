package com.ruoyi.studentSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.studentSys.domain.SysDistrict;
import com.ruoyi.studentSys.domain.TCls;
import com.ruoyi.studentSys.domain.TongJiInt;
import com.ruoyi.studentSys.service.ISysDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentSys.mapper.TStudentMapper;
import com.ruoyi.studentSys.domain.TStudent;
import com.ruoyi.studentSys.service.ITStudentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学生信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-09-22
 */
@Service
public class TStudentServiceImpl implements ITStudentService {
    @Autowired
    private TStudentMapper tStudentMapper;
    @Autowired
    private ISysDistrictService districtService;

    /**
     * 查询学生信息
     *
     * @param stuId 学生信息主键
     * @return 学生信息
     */
    @Override
    public TStudent selectTStudentByStuId(String stuId) {
        return tStudentMapper.selectTStudentByStuId(stuId);
    }

    /**
     * 查询学生信息列表
     *
     * @param tstudent 学生信息
     * @return 学生信息
     */
    @Override
    public List<TStudent> selectTStudentList(TStudent tstudent) {
        return tStudentMapper.selectTStudentList(tstudent);
    }


    /**
     * 新增学生信息
     *
     * @param tStudent 学生信息
     * @return 结果
     */
    @Override
    public int insertTStudent(TStudent tStudent) {
        tStudent.setCreateBy(ShiroUtils.getLoginName());
        tStudent.setCreateTime(DateUtils.getNowDate());
        return tStudentMapper.insertTStudent(tStudent);
    }

    /**
     * 修改学生信息
     *
     * @param tStudent 学生信息
     * @return 结果
     */
    @Override
    public int updateTStudent(TStudent tStudent) {
        tStudent.setUpdateTime(DateUtils.getNowDate());
        return tStudentMapper.updateTStudent(tStudent);
    }

    /**
     * 批量删除学生信息
     *
     * @param stuIds 需要删除的学生信息主键
     * @return 结果
     */
    @Override
    public int deleteTStudentByStuIds(String stuIds) {
        return tStudentMapper.deleteTStudentByStuIds(Convert.toStrArray(stuIds));
    }

    /**
     * 删除学生信息信息
     *
     * @param stuId 学生信息主键
     * @return 结果
     */
    @Override
    public int deleteTStudentByStuId(String stuId) {
        return tStudentMapper.deleteTStudentByStuId(stuId);
    }

    @Override
    public List<TongJiInt> selectCountByProvince() {
        List<TongJiInt> list = tStudentMapper.selectCountByProvince();
        List<TongJiInt> listNew = new ArrayList<>();

        SysDistrict sysDistrict = new SysDistrict();
        sysDistrict.setParentId("0");
        List<SysDistrict> pList = districtService.selectSysDistrictList(sysDistrict);

        for (SysDistrict district : pList) {
            TongJiInt target = list.stream()
                    .filter(tongji -> tongji.getName().equals(district.getDistrictId()))
                    .findFirst()
                    .orElse(null); // 如果没找到返回null
            if (target == null)
                continue;
            listNew.add(new TongJiInt(district.getDistrictName(), target.getValue()));
        }
        return listNew;
    }
}