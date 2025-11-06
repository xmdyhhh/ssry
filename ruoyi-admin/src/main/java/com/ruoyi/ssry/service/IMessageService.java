package com.ruoyi.ssry.service;

import com.ruoyi.ssry.domain.Message;

import java.util.List;

public interface IMessageService {
    List<Message> getMessagesForStudent(String id);

    boolean markMessageAsRead(Long id);

    Message findById(Long id);

    List<Message> getMessagesForTeacher(String id);

    boolean markAllMessagesAsRead(String receiverType, String receiverId);

    List<Message> adminMessagelist(String id);

    void adminsendMessage(
            String title, String content,
            String senderType, Long senderId, String senderName,
            String receiverType, Long receiverId
    );

    void sendApplicationMessage(
            String title,
            String content,
            String senderType,
            Long senderId,
            String senderName,
            String receiverType,
            Long receiverId
    );

    void approve(Long id);

    void reject(Long id);


    void sendReplyFromSystem(String title, String content, String toType, Long teacherId,String appStatue);

    void sendReplyFromTeacher(String title, String content, String toType, Long studentId, String appStatue);
}
