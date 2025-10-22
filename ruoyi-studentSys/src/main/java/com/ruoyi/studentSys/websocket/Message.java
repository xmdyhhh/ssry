package com.ruoyi.studentSys.websocket;


/**
 * 消息对象
 * @author 
 * @date 2023/11/24 23:48
 */
public class Message implements java.io.Serializable{

    private static final long serialVersionUID = 1L;


    private String fromName;

    /**
     * 发送给谁
     */
    private String toName;
    /**
     * 消息
     */
    private String content;

    private String time;
    private String type;  //message  broadcast  online  offline  system friends


   private Object contentObject;


    public Object getContentObject() {
        return contentObject;
    }

    public void setContentObject(Object contentObject) {
        this.contentObject = contentObject;
    }

    public Message() {
    }

    public Message(String fromName, String toName, String content, String time, String type) {
        this.fromName = fromName;
        this.toName = toName;
        this.content = content;
        this.time = time;
        this.type = type;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
