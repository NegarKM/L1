package com.loyaltyone.interview.web.bean;

import java.sql.Timestamp;

public class PostVO {
    private String text;

    private Timestamp timestamp;

    private String username;

    public PostVO() {
    }

    public PostVO(String text) {
        this.text = text;
    }

    public PostVO(String text, Timestamp timestamp, String username) {
        this.text = text;
        this.timestamp = timestamp;
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
