package com.loyaltyone.interview.web.bean;

import java.sql.Timestamp;

public class PostVO {
    private Long id;

    private String text;

    private Timestamp timestamp;

    private String username;

    private CityVO cityVO;

    public PostVO() {
    }

    public PostVO(String text) {
        this.text = text;
    }

    public PostVO(Long id, String text, Timestamp timestamp, String username, CityVO cityVO) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.username = username;
        this.cityVO = cityVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CityVO getCityVO() {
        return cityVO;
    }

    public void setCityVO(CityVO cityVO) {
        this.cityVO = cityVO;
    }
}
