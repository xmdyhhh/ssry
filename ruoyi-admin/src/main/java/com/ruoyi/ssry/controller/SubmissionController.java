package com.ruoyi.ssry.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.*;
import com.ruoyi.ssry.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ssry/submission")
public class SubmissionController {

    @Autowired
    private IHomeworkSubmissionService submissionService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IHomeworkService homeworkService;
    @Autowired
    private IMessageService messageService;

    @GetMapping("/courselist")
    public String courseList(){
        return "ssry/homework/courselist";
    }

    @ResponseBody
    @PostMapping("/getcourselist")
    public AjaxResult tclist(){
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Teacher teacher = teacherService.selectTeacherByteacherno(loginName);
        List<Course> list = teacherService.getcourselist(teacher.getId());
        return AjaxResult.success(list);
    }

    @GetMapping("/homeworklist")
    public String homeworkList(){
        return "ssry/homework/homeworklist";
    }

    @RequestMapping("/gethomeworklist")
    @ResponseBody
    public AjaxResult getHomeworkList(@RequestParam String courseId){
        List<Homework> list = submissionService.getHomeworkList(courseId);
        return AjaxResult.success(list);
    }

    @GetMapping("/submissionlist")
    public String submissionList(){
        return "ssry/homework/submissionlist";
    }
    @RequestMapping("/getsubmissionlist")
    @ResponseBody
    public AjaxResult getSubmissionList(@RequestParam String homeworkId){
        List<HomeworkSubmission> list = submissionService.getSubmissionsByhomeworkId(homeworkId);
        return AjaxResult.success(list);
    }


    @PostMapping("/updateScore")
    @ResponseBody
    public AjaxResult updateScore(@RequestBody HomeworkSubmission submission) {
        if (submission.getId() == null) {
            return AjaxResult.error("ID不能为空");
        }
        submissionService.updateScore(submission);
        return AjaxResult.success();
    }
    @PostMapping("/sendReminder")
    @ResponseBody
    public AjaxResult sendReminder(@RequestParam String homeworkId) {
        try {
            if (homeworkId == null || homeworkId.trim().isEmpty()) {
                return AjaxResult.error("作业ID不能为空");
            }

            SysUser user = ShiroUtils.getSysUser();
            String loginName = user.getLoginName();
            Teacher teacher = teacherService.selectTeacherByteacherno(loginName);

            // 验证作业是否存在
            Homework homework = homeworkService.getHomeworkById(homeworkId);
            if (homework == null) {
                return AjaxResult.error("作业不存在");
            }

            List<Student> students = homeworkService.getStudentListByHomeworkId(homeworkId);
            String title = homework.getTitle();

            for (Student student : students) {
                messageService.sendRemider(
                        student.getId(),
                        title,
                        Long.valueOf(teacher.getId()),
                        teacher.getName()
                );
            }

            return AjaxResult.success("提醒已发送");

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("发送失败：" + e.getMessage());
        }
    }
}
