package com.loyaltyone.interview.web.bean;

public class PostVO {
    private String text;

    public PostVO() {
    }

    public PostVO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
