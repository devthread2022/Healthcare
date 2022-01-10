package com.jvt.devthread.healthcare.Activities.Model;

public class PatientStoriesModel {
    String date,feedback,name,reason;

    public PatientStoriesModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public PatientStoriesModel(String date, String feedback, String name, String reason) {
        this.date = date;
        this.feedback = feedback;
        this.name = name;
        this.reason = reason;
    }
}
