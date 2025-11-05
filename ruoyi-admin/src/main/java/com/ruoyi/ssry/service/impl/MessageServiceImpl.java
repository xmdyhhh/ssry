package com.ruoyi.ssry.service.impl;

import com.ruoyi.ssry.domain.Message;
import com.ruoyi.ssry.domain.Student;
import com.ruoyi.ssry.domain.Teacher;
import com.ruoyi.ssry.mapper.MessageMapper;
import com.ruoyi.ssry.service.IMessageService;
import com.ruoyi.ssry.service.IStudentService;
import com.ruoyi.ssry.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.core.NestedExceptionUtils.buildMessage;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;

    @Override
    public List<Message> getMessagesForStudent(String id) {
        return messageMapper.selectNoticeByReceiver("student", Long.parseLong(id));
    }

    @Override
    public boolean markMessageAsRead(Long id) {
        return messageMapper.updateAsRead(id) > 0;
    }

    @Override
    public boolean markAllMessagesAsRead(String receiverType, String receiverId) {
        return messageMapper.markAllMessagesAsRead(receiverType, receiverId) > 0;
    }

    @Override
    public List<Message> adminMessagelist(String id) {
        return messageMapper.selectadminNoticeByReceiver("admin", Long.parseLong(id));
    }

    @Override
    public Message findById(Long id) {
        return messageMapper.selectById(id);
    }

    @Override
    public List<Message> getMessagesForTeacher(String id) {
        return messageMapper.selectNoticeByReceiver("teacher", Long.parseLong(id));
    }

    @Override
    public void adminsendMessage(String title, String content, String senderType, Long senderId, String senderName,
                            String receiverType, Long receiverId) {

        List<Message> messages = new ArrayList<>();

        switch (receiverType) {
            case "all_students":
                studentService.selectStudentList(new Student()).forEach(s ->
                        messages.add(buildMessage(title, content, senderType, senderId, senderName, "student", Long.valueOf(s.getId())))
                );
                break;

            case "all_teachers":
                teacherService.selectTeacherList(new Teacher()).forEach(t ->
                        messages.add(buildMessage(title, content, senderType, senderId, senderName, "teacher", Long.valueOf(t.getId())))
                );
                break;

            case "college_students":
                studentService.selectStudentsByCollegeId(receiverId).forEach(s ->
                        messages.add(buildMessage(title, content, senderType, senderId, senderName, "student", Long.valueOf(s.getId())))
                );
                break;

            case "college_teachers":
                teacherService.selectTeachersByCollegeId(receiverId).forEach(t ->
                        messages.add(buildMessage(title, content, senderType, senderId, senderName, "teacher", Long.valueOf(t.getId())))
                );
                break;

            default:
                throw new IllegalArgumentException("不支持的接收类型: " + receiverType);
        }

        if (!messages.isEmpty()) {
            messageMapper.batchInsert(messages);
        }
    }

    @Override
    public void sendApplicationMessage(String title, String content,
                                       String senderType, Long senderId, String senderName,
                                       String receiverType, Long receiverId) {
        Message msg = new Message();
        msg.setTitle(title);
        msg.setContent(content);
        msg.setSenderType(senderType);
        msg.setSenderId(senderId);
        msg.setSenderName(senderName);
        msg.setReceiverType(receiverType);
        msg.setReceiverId(receiverId);
        msg.setMsgType("application"); // 或保留 "notice"，用 isApplication 区分
        msg.setIsApplication(true);
        msg.setAppStatus("pending");
        msg.setIsRead(false);
        msg.setCreateTime(new Date());

        messageMapper.insert(msg);
    }
    private Message buildMessage(String title, String content, String senderType, Long senderId, String senderName,
                                 String receiverType, Long receiverId) {
        Message msg = new Message();
        msg.setTitle(title);
        msg.setContent(content);
        msg.setSenderType(senderType);
        msg.setSenderId(senderId);
        msg.setSenderName(senderName);
        msg.setReceiverType(receiverType);
        msg.setReceiverId(receiverId);
        msg.setMsgType("notice");
        msg.setIsRead(false);
        msg.setCreateTime(new Date());
        return msg;
    }
}