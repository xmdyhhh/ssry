package com.ruoyi.ssry.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.ssry.domain.Admin;
import com.ruoyi.ssry.domain.Message;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.domain.Teacher;
import com.ruoyi.ssry.service.IAdminService;
import com.ruoyi.ssry.service.IMessageService;
import com.ruoyi.ssry.service.IStudentService;
import com.ruoyi.ssry.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ssry/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IAdminService adminService;

    @PostMapping("/studentMessagelist")
    @ResponseBody
    public AjaxResult studentMessagelist() {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Student student = studentService.selectStudentBystudentno(loginName);
        List<Message> messages = messageService.getMessagesForStudent(student.getId());
        return AjaxResult.success(messages);
    }

    @GetMapping("/studentMessage")
    public String studentMessage() {
        return "ssry/message/studentMessagelist";
    }

    @PostMapping("/teacherMessagelist")
    @ResponseBody
    public AjaxResult teacherMessagelist() {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Teacher teacher = teacherService.selectTeacherByteacherno(loginName);
        List<Message> messages = messageService.getMessagesForTeacher(teacher.getId());
        return AjaxResult.success(messages);
    }

    @GetMapping("/teacherMessage")
    public String teacherMessage() {
        return "ssry/message/teacherMessagelist";
    }

    @PostMapping("/adminMessagelist")
    @ResponseBody
    public AjaxResult adminMessagelist() {
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Admin admin = adminService.selectAdmin(loginName);
        List<Message> messages = messageService.adminMessagelist(admin.getId());
        return AjaxResult.success(messages);
    }

    @GetMapping("/adminMessage")
    public String adminMessage() {
        return "ssry/message/adminMessagelist";
    }

    @PostMapping("/markRead")
    @ResponseBody
    public AjaxResult markRead(@RequestParam Long id) {
        boolean result = messageService.markMessageAsRead(id);
        if (result) {
            return AjaxResult.success("标记成功");
        } else {
            return AjaxResult.error("标记失败");
        }
    }

    @PostMapping("/markAllRead")
    @ResponseBody
    public AjaxResult markAllRead() { // 不再需要 @RequestParam Long id
        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Teacher teacher = teacherService.selectTeacherByteacherno(loginName);
        if (teacher != null) {
            boolean result = messageService.markAllMessagesAsRead("teacher", teacher.getId());
            return result ? AjaxResult.success("标记成功") : AjaxResult.error("标记失败");
        } else {
            Student student = studentService.selectStudentBystudentno(loginName);
            if (student != null) {
                boolean result = messageService.markAllMessagesAsRead("student", student.getId());
                return result ? AjaxResult.success("标记成功") : AjaxResult.error("标记失败");
            }
        }
        return AjaxResult.error("用户类型未知");
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        // 调用 service 获取消息详情
        Message message = messageService.findById(id);
        if (message == null) {
            throw new RuntimeException("消息不存在");
        }

        // 如果未读，可在此自动标记为已读（可选）
        if (message.getIsRead() == false) {
            messageService.markMessageAsRead(id);
            message.setIsRead(true); // 同步前端状态
        }

        model.addAttribute("message", message);
        return "ssry/message/detail";
    }
}