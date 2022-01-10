package com.jvt.devthread.healthcare.Activities.Model;

public class TimeModel {
    String timeId, time;

    public TimeModel() {
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TimeModel(String timeId, String time) {
        this.timeId = timeId;
        this.time = time;
    }
}
