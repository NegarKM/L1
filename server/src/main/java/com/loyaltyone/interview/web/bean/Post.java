package com.loyaltyone.interview.web.bean;

public class Post {
    private String text;

    public Post() {
    }

    public Post(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
