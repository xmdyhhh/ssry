package com.ruoyi.ssry.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private Long id;
    private String title;
    private String content;
    private String senderType;
    private Long senderId;
    private String receiverType;
    private String msgType;
    private Boolean isApplication;
    private String appStatus;
    private String adminRemark;
    private Boolean isRead;
    private Date createTime;
    private String senderName;
    private Long receiverId;
}