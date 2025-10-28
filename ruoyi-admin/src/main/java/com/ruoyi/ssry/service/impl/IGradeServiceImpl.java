package com.ruoyi.ssry.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ssry.domain.Course;
import com.ruoyi.ssry.domain.Grade;
import com.ruoyi.ssry.domain.TJInt;
import com.ruoyi.ssry.domain.Teacher;
import com.ruoyi.ssry.mapper.CourseMapper;
import com.ruoyi.ssry.mapper.GradeMapper;
import com.ruoyi.ssry.service.ICourseService;
import com.ruoyi.ssry.service.IGradeService;
import com.ruoyi.ssry.service.ITeacherService;
import com.ruoyi.studentSys.domain.TongJiInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class IGradeServiceImpl implements IGradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private ITeacherService teacherService;

    @Override
    public List<Grade> studentgradelist(String studentid) {
        return gradeMapper.studentgradelist(studentid);
    }

    @Override
    public List<Grade> getcourselist(String courseId) {
        return gradeMapper.getcourselist(courseId);
    }

    @Override
    public Grade getGradeById(Long id) {
        return gradeMapper.selectGradeById(id);
    }

    @Override
    public int updateGrade(Grade grade) {
        if (grade.getId() == null) {
            throw new RuntimeException("成绩记录ID不能为空");
        }

        Grade old = gradeMapper.selectGradeById(grade.getId());
        if (old == null) {
            throw new RuntimeException("成绩记录不存在");
        }

        // 校验分数
        validateScore(grade.getUsualScore(), "平时成绩");
        validateScore(grade.getFinalScore(), "期末成绩");

        // 如果没有提供新分数，不需要重新计算totalScore
        if (grade.getUsualScore() == null && grade.getFinalScore() == null) {
            // 只更新其他字段
            return gradeMapper.updateGrade(grade);
        }

        // 使用数据库或课程表中的权重（当前写死为40/60）
        BigDecimal usualWeight = BigDecimal.valueOf(40);
        BigDecimal finalWeight = BigDecimal.valueOf(60);

        // 使用新值或旧值
        BigDecimal usual = grade.getUsualScore() != null ? grade.getUsualScore() : old.getUsualScore();
        BigDecimal finals = grade.getFinalScore() != null ? grade.getFinalScore() : old.getFinalScore();

        BigDecimal total = usual.multiply(usualWeight)
                .add(finals.multiply(finalWeight))
                .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

        grade.setTotalScore(total);

        return gradeMapper.updateGrade(grade);
    }

    private void validateScore(BigDecimal score, String name) {
        if (score != null &&
                (score.compareTo(BigDecimal.ZERO) < 0 || score.compareTo(BigDecimal.valueOf(100)) > 0)) {
            throw new RuntimeException(name + "必须在0-100之间");
        }
    }

    @Override
    public List<TJInt> tsgradelist(String courseId) {

        int count90 = gradeMapper.selectRangeByCourseId(courseId,90,101);
        int count80 = gradeMapper.selectRangeByCourseId(courseId,80,90);
        int count70 = gradeMapper.selectRangeByCourseId(courseId,70,80);
        int count60 = gradeMapper.selectRangeByCourseId(courseId,60,70);
        int count0 = gradeMapper.selectRangeByCourseId(courseId,0,60);
        List<TJInt> list = new ArrayList<>();
        list.add(new TJInt("90以上",count90));
        list.add(new TJInt("80-90",count80));
        list.add(new TJInt("70-80",count70));
        list.add(new TJInt("60-70",count60));
        list.add(new TJInt("60以下",count0));
        return list;
    }

    @Autowired
    private ICourseService courseService;

    @Override
    public AjaxResult getzhubycourseid(String courseId) {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Teacher teacher = teacherService.selectTeacherByteacherno(loginName);
        List<Course> courses = teacherService.getcourselist(teacher.getId());
        ArrayList<String> courseNames = new ArrayList<>();
        ArrayList<Float> avgList = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getCourseName());
            float avg = gradeMapper.selectAvgByCourseId(teacher.getId());
            avgList.add(avg);
        }
        AjaxResult success = AjaxResult.success();
        success.put("courseNameList",courseNames);
        success.put("avgList",avgList);
        return success;
    }

//    @Override
//    public AjaxResult getCourseYearlyAvg(String courseId) {
//        SysUser user = ShiroUtils.getSysUser();
//        String loginName = user.getLoginName();
//        Teacher teacher = teacherService.selectTeacherByteacherno(loginName);
//        List<Course> courses = teacherService.getcourselist(teacher.getId());
//        ArrayList<String> years = new ArrayList<>();
//        ArrayList<Float> avgList = new ArrayList<>();
//        for (Course course : courses){
//            years.add(gradeMapper.getcourseyear(course.getId(), teacher.getId()));
//        }
//        for (Course course : courses){
//            avgList.add(gradeMapper.getcourseyearavg(course.getId(), teacher.getId()));
//        }
//        AjaxResult success = AjaxResult.success();
//        success.put("years",years);
//        success.put("avgList",avgList);
//        return success;
//    }
    @Override
    public AjaxResult getCourseYearlyAvg(String courseId) {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Teacher teacher = teacherService.selectTeacherByteacherno(loginName);

        // 如果没传 courseId，则默认查第一个课程
        if (StringUtils.isEmpty(courseId)) {
            List<Course> courses = teacherService.getcourselist(teacher.getId());
            if (courses.isEmpty()) {
                return AjaxResult.error("该教师未绑定任何课程");
            }
            courseId = courses.get(0).getId(); // 取第一个课程
        }

        // 查询该课程历年平均分（已按年分组）
        List<Map<String, Object>> yearlyData = gradeMapper.selectYearlyAvgByCourseAndTeacher(courseId, teacher.getId());

        List<String> years = new ArrayList<>();
        List<Float> avgList = new ArrayList<>();

        for (Map<String, Object> row : yearlyData) {
            years.add(row.get("year").toString());
            avgList.add(((BigDecimal) row.get("avgScore")).floatValue()); // AVG 是 BigDecimal
        }

        AjaxResult result = AjaxResult.success();
        result.put("years", years);
        result.put("avgList", avgList);
        return result;
    }
}