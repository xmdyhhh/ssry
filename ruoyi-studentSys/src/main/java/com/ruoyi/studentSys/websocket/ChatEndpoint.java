package com.ruoyi.studentSys.websocket;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;



/**
 * 处理具体websocket消息的接口
 * @author
 * @date 2023/11/23 21:19
 */
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndpoint {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final Map<String,Session> onlineUsers = new ConcurrentHashMap<>();

    private HttpSession httpSession;
    /**
     * 连接建立时被调用
     * @param session Session
     * @param config EndpointConfig
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        //获取httpSession并赋值，便于后面OnMessage（）和OnClose（）时使用
        this.httpSession = (HttpSession)config.getUserProperties()
                .get(HttpSession.class.getName());

        SysUser user = (SysUser)httpSession.getAttribute("user");

        // 添加空值检查
        if (user == null) {
            logger.error("WebSocket连接失败：用户未登录或会话已过期");
            session.close(new CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY, "用户未登录"));
            return;
        }

        //1、 将session进行保存
        onlineUsers.put(user.getLoginName(), session);
        logger.info("用户【{}】已上线", user.getLoginName());
        String msgStr = MessageUtils.getMessageFriends(user.getLoginName(), onlineUsers);
        session.getBasicRemote().sendText(msgStr);

        //2、广播消息，需要将登录的所有用户推送给其他所有用户
        String message = MessageUtils.getMessage(user.getLoginName(), null, user.getLoginName()+"上线了", DateUtils.getTime(), "online");
        broadcastAllUsers(message);
    }


    private Set<String> getFriends(){
        return  onlineUsers.keySet();
    }

    /**
     * 广播消息，推送给其他所有用户
     * @param message 消息
     */
    private void broadcastAllUsers(String message) {
        if (httpSession == null) {
            logger.error("广播消息时HTTP会话为空");
            return;
        }

        SysUser user = (SysUser) httpSession.getAttribute("user");
        if (user == null) {
            logger.error("广播消息时用户未登录");
            return;
        }

        logger.info("当前用户【{}】，广播消息给其他所有用户消息：【{}】", user.getLoginName(), message);

        Set<Map.Entry<String, Session>> entries = onlineUsers.entrySet();
        for (Map.Entry<String, Session> entry : entries) {
            Session session = entry.getValue();
            //发送消息
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.info("当前用户【{}】，广播消息失败，失败原因为：【{}】", user, e.getMessage());
            }
        }
    }


    /**
     * 浏览器发送消息到服务端，接收到客户端发送的数据时该方法被调用
     * @param message 消息 数据格式: {"toName":"张三","message":"你好..."}
     */
    @OnMessage
    public void onMessage(String message) {
        // 添加用户空值检查
        SysUser currentUser = (SysUser) httpSession.getAttribute("user");
        if (currentUser == null) {
            logger.error("处理消息时用户未登录");
            return;
        }

        //将消息推送给指定的用户
        Message msg = JSON.parseObject(message, Message.class);
        //获取指定用户的用户名
        String toName = msg.getToName();
        //获取发送的消息
        String sendMsg = msg.getContent();

        msg.setFromName(currentUser.getLoginName());
        msg.setTime(DateUtils.getTime());
        msg.setType("message");

        String msgStr = JSON.toJSONString(msg);

        Session session = onlineUsers.get(toName);
        if (session == null) {
            logger.info("当前用户【{}】,给指定用户【{}】,发送了消息失败，失败原因:【{}】", currentUser.getLoginName(), toName, "用户不在线");
            try {
                msgStr = MessageUtils.getMessage(null, currentUser.getLoginName(), toName + "不在线",
                        DateUtils.getTime(), "system");
                Session sessionFrom = onlineUsers.get(currentUser.getLoginName());
                if (sessionFrom != null) {
                    sessionFrom.getBasicRemote().sendText(msgStr);
                }
            } catch (IOException e) {
                logger.info("当前用户【{}】,给指定用户【{}】,发送了消息失败，失败原因:【{}】", currentUser.getLoginName(), toName, e.getMessage());
            }
            return;
        }

        try {
            session.getBasicRemote().sendText(msgStr);
            logger.info("当前用户【{}】,给指定用户【{}】,发送了消息:【{}】", currentUser.getLoginName(), toName, sendMsg);
        } catch (IOException e) {
            logger.info("当前用户【{}】,给指定用户【{}】,发送了消息失败，失败原因:【{}】", currentUser.getLoginName(), toName, e.getMessage());
        }
    }


    /**
     * 连接关闭时被调用
     * @param session Session
     */
    @OnClose
    public void onClose(Session session) {
        // 添加空值检查
        if (httpSession != null) {
            SysUser user = (SysUser) httpSession.getAttribute("user");
            if (user != null) {
                //1、 从 onlineUsers 中删除当前用户的session对象
                onlineUsers.remove(user.getLoginName());
                logger.info("用户【{}】已下线", user.getLoginName());

                //2、通知其他所有的用户，当前用户下线了。
                String message = MessageUtils.getMessage(user.getLoginName(), null, user.getLoginName() + "下线了", DateUtils.getTime(), "offline");
                broadcastAllUsers(message);
            } else {
                logger.warn("WebSocket连接关闭时用户信息为空");
            }
        } else {
            logger.warn("WebSocket连接关闭时HTTP会话为空");
        }
    }



}
