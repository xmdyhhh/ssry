package com.ruoyi.studentSys.websocket;

import java.util.List;

public class Friend {
    private String id;               // （如"admin"）

    private String name;               // 好友姓名（如"张三"）
    private String avatar;             // 头像路径（如"/avatar/1.jpg"）
    private String status;             // 在线状态（如"online"）
    private String statusText;         // 状态描述（如"正在输入..."）
    private List<Message> messages;    // 聊天记录列表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }





}