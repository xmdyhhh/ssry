package com.ruoyi.ssry.service.impl;

import com.ruoyi.ssry.domain.Message;
import com.ruoyi.ssry.mapper.MessageMapper;
import com.ruoyi.ssry.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> getMessagesForStudent(String id) {
        return messageMapper.selectNoticeByReceiver("student", Long.parseLong(id));
    }

    @Override
    public boolean markMessageAsRead(Long id) {
        return messageMapper.updateAsRead(id) > 0;
    }

    @Override
    public String markAllMessageAsRead() {
        return messageMapper.markAllMessageAsRead() > 0 ? "success" : "fail";
    }

    @Override
    public Message findById(Long id) {
        return messageMapper.selectById(id);
    }
}
