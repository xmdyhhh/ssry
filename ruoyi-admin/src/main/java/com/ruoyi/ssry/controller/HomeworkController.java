package com.ruoyi.ssry.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.*;
import com.ruoyi.ssry.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Controller
@RequestMapping("/ssry/homework")
public class HomeworkController {

    @Autowired
    private IHomeworkService homeworkService;
    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/studenthomeworklist")
    public String homeworkList(Model model) {
        List<Homework> homeworkList = homeworkService.getStudentHomework();
        model.addAttribute("homeworkList", homeworkList);
        return "ssry/homework/studenthomeworklist";
    }

    @PostMapping("/studenthomework")
    @ResponseBody
    public AjaxResult  getStudentHomework() {
        List<Homework> list = homeworkService.getStudentHomework();
        return AjaxResult.success(list);
    }


    @PostMapping("/addhomework")
    @ResponseBody
    public AjaxResult addHomework(@RequestBody HomeworkDTO dto) {
        try {
            Homework homework = new Homework();
            homework.setTitle(dto.getTitle());
            homework.setContent(dto.getContent() != null && !dto.getContent().trim().isEmpty()
                    ? dto.getContent() : ""); // 防止 null
            homework.setCourseId(dto.getCourseId());
            homework.setRequiredFormat(dto.getRequiredFormat() != null && !dto.getRequiredFormat().trim().isEmpty()
                    ? dto.getRequiredFormat() : "pdf,doc");
            homework.setMaxSizeMb(dto.getMaxSizeMb() != null ? dto.getMaxSizeMb() : 10);
            // 解析 deadline
            if (dto.getDeadline() != null && !dto.getDeadline().isEmpty()) {
                String dt = dto.getDeadline();
                if (dt.length() == 16 && dt.charAt(10) == 'T') {
                    LocalDate dueDate = LocalDate.parse(dt.substring(0, 10));
                    LocalTime dueTime = LocalTime.parse(dt.substring(11));
                    homework.setDueDate(dueDate);
                    homework.setDueTime(dueTime);
                } else {
                    return AjaxResult.error("截止时间格式错误");
                }
            } else {
                return AjaxResult.error("截止时间不能为空");
            }

            // 系统字段
            homework.setIssueDate(LocalDate.now());
            homework.setCreateTime(LocalDateTime.now());
            homework.setUpdateTime(LocalDateTime.now());
            SysUser user = ShiroUtils.getSysUser();
            String loginName = user.getLoginName();
            Teacher teacher = teacherService.selectTeacherByteacherno(loginName);
            homework.setCreatedBy(Long.parseLong(teacher.getId()));

            homeworkService.addHomework(homework);
            return AjaxResult.success("作业添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("作业添加失败：" + e.getMessage());
        }
    }

    @DeleteMapping("/deletehomework/{id}")
    @ResponseBody
    public AjaxResult deleteHomework(@PathVariable Long id) {
        return homeworkService.deleteHomeworkById(id) ? AjaxResult.success("作业删除成功") : AjaxResult.error("作业删除失败");
    }

}
