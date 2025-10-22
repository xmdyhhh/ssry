package com.ruoyi.studentSys.websocket;

/**
 * 系统消息对象
 * 系统消息格式：{"isSystemMessage":true,"fromName":null,"message":["李四","王五"]}
 * 推送给某一用户的消息格式：{"isSystemMessage":false,"fromName":"张三","message":"你好"}
 *
 * @author 
 * @date 2023/11/23 21:01
 */

public class ResultMessage implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 是否是系统消息，true-是系统消息 flase-用户消息
     */
    private boolean systemMessage;

    /**
     * 消息
     */
    private Object message;

    /**
     * 谁发送的消息
     */
    private String fromName;

    public ResultMessage() {
    }

    public ResultMessage(boolean systemMessage, Object message, String fromName) {
        this.systemMessage = systemMessage;
        this.message = message;
        this.fromName = fromName;
    }

    public boolean isSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(boolean systemMessage) {
        this.systemMessage = systemMessage;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
}
