package com.example.myapplication.bean;

import java.time.LocalDateTime;

public class Message {
    private Integer id;

    /**
     * 发送者的用户id
     */
    private Integer fromId;

    /**
     * 接受者的用户id
     */
    private Integer toId;

    /**
     * 发送的内容
     */
    private String content;

    /**
     * 发送的时间
     */
    private String time;


    /**
     * session会话的id
     */
    private String sessionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
