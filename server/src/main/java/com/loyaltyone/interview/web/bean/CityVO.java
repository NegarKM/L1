package com.loyaltyone.interview.web.bean;

public class CityVO {
    private String name;

    private String latitude;

    private String longitude;

    private String temperature;

    public CityVO(String name, String latitude, String longitude, String temperature) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
