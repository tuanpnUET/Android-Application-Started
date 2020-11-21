package com.itplus.weather;

public class Weather {
    String time;
    String state;
    String urlIcon;
    String min;
    String max;

    public Weather(String time, String state, String urlIcon, String min, String max) {
        this.time = time;
        this.state = state;
        this.urlIcon = urlIcon;
        this.min = min;
        this.max = max;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}

