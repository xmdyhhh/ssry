package com.ruoyi.ssry.service;

import com.ruoyi.ssry.domain.Message;

import java.util.List;

public interface IMessageService {
    List<Message> getMessagesForStudent(String id);

    boolean markMessageAsRead(Long id);

    String markAllMessageAsRead();

    Message findById(Long id);
}
