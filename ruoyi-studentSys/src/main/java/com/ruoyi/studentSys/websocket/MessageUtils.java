package com.ruoyi.studentSys.websocket;

import com.alibaba.fastjson.JSON;

import javax.websocket.Session;
import java.util.Map;

/**
 * 封装json格式的消息工具类
 *
 * @author 
 * @date 2023/11/23 21:03
 */
public class MessageUtils {

    public static String getMessage(String fromName, String toName, String content, String time, String type) {
        Message message = new Message(fromName, toName, content, time, type) ;
        return JSON.toJSONString(message);
    }



    public static String getMessageFriends( String toName, Map<String, Session> onlineUsers) {

        Message message = new Message(null, toName, "", "", "friends") ;
        message.setContentObject(onlineUsers.keySet());
        return JSON.toJSONString(message);
    }



}
