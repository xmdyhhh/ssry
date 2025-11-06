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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/adminsend")
    public String sendPage(Model model) {
        // 可选：加载学院列表、教师列表、学生列表（或通过异步接口加载）
        return "ssry/message/adminsendMessage";
    }

    @PostMapping("/adminsend")
    @ResponseBody
    public AjaxResult sendMessage(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String receiverType,
            @RequestParam(required = false) Long receiverId) {

        SysUser user = ShiroUtils.getSysUser();
        String loginName = user.getLoginName();
        Admin admin = adminService.selectAdmin(loginName);
        Long senderId = Long.parseLong(admin.getId());

        try {
            messageService.adminsendMessage(
                    title, content,
                    "system", senderId, admin.getName(),
                    receiverType, receiverId
            );
            return AjaxResult.success("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("发送失败：" + e.getMessage());
        }
    }
    @GetMapping("/studentsend")
    public String studentsendPage(Model model) {
        return "ssry/message/studentsendMessage";
    }

    @GetMapping("/studentApply")
    public String studentApplyPage() {
        return "ssry/message/studentApply"; // 前端页面：选择老师 + 填写表单
    }

    @PostMapping("/studentApply")
    @ResponseBody
    public AjaxResult studentApply(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam Long teacherId) { // 目标老师 ID

        SysUser user = ShiroUtils.getSysUser();
        Student student = studentService.selectStudentBystudentno(user.getLoginName());

        try {
            messageService.sendApplicationMessage(
                    title, content,
                    "student", Long.parseLong(student.getId()), student.getName(),
                    "teacher", teacherId
            );
            return AjaxResult.success("申请已提交");
        } catch (Exception e) {
            return AjaxResult.error("提交失败：" + e.getMessage());
        }
    }
    @GetMapping("/teacherApply")
    public String teacherApplyPage() {
        return "ssry/message/teacherApply";
    }

    @PostMapping("/teacherApply")
    @ResponseBody
    public AjaxResult teacherApply(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam Long adminId) { // 目标管理员 ID

        SysUser user = ShiroUtils.getSysUser();
        Teacher teacher = teacherService.selectTeacherByteacherno(user.getLoginName());

        try {
            messageService.sendApplicationMessage(
                    title, content,
                    "teacher", Long.parseLong(teacher.getId()), teacher.getName(),
                    "admin", adminId
            );
            return AjaxResult.success("申请已提交");
        } catch (Exception e) {
            return AjaxResult.error("提交失败：" + e.getMessage());
        }
    }

    @PostMapping("/approve/{id}")
    public String approveApplication(
            @PathVariable Long id,
            @RequestParam String reason, // 新增参数
            RedirectAttributes redirectAttrs) {

        Message original = messageService.findById(id);
        if (!"pending".equals(original.getAppStatus()) || !original.getIsApplication()) {
            throw new IllegalArgumentException("非待审批申请");
        }

        // 更新原申请状态
        messageService.approve(id);

        // 发送带说明的回复
        if ("admin".equals(original.getReceiverType())) {
            messageService.sendReplyFromSystem(
                    "申请已批准", reason, // 使用用户输入的 reason
                    original.getSenderType(), original.getSenderId(), "approve"
            );
        } else if ("teacher".equals(original.getReceiverType())) {
            messageService.sendReplyFromTeacher(
                    "申请已批准", reason, // 使用用户输入的 reason
                    original.getSenderType(), original.getSenderId(), "approve"
            );
        } else {
            throw new IllegalStateException("未知的接收者类型: " + original.getReceiverType());
        }

        redirectAttrs.addFlashAttribute("msg", "申请已批准");
        return "redirect:/ssry/message/detail/" + id;
    }
    @PostMapping("/reject/{id}")
    public String rejectApplication(
            @PathVariable Long id,
            @RequestParam String reason, // 新增参数
            RedirectAttributes redirectAttrs) {

        Message original = messageService.findById(id);
        if (!"pending".equals(original.getAppStatus()) || !original.getIsApplication()) {
            throw new IllegalArgumentException("非待审批申请");
        }

        // 更新状态为 rejected
        messageService.reject(id);

        // 发送拒绝理由
        if ("admin".equals(original.getReceiverType())) {
            messageService.sendReplyFromSystem(
                    "申请未获批准", reason,
                    original.getSenderType(), original.getSenderId(), "reject"
            );
        } else if ("teacher".equals(original.getReceiverType())) {
            messageService.sendReplyFromTeacher(
                    "申请未获批准", reason,
                    original.getSenderType(), original.getSenderId(), "reject"
            );
        } else {
            throw new IllegalStateException("未知的接收者类型");
        }

        redirectAttrs.addFlashAttribute("msg", "申请已拒绝");
        return "redirect:/ssry/message/detail/" + id;
    }
}