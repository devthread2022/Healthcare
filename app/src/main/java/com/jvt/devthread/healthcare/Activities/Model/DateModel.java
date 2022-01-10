package com.jvt.devthread.healthcare.Activities.Model;

public class DateModel {
    String date, dayName, dateId;
    Integer availableSlot;

    public DateModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    public Integer getAvailableSlot() {
        return availableSlot;
    }

    public void setAvailableSlot(Integer availableSlot) {
        this.availableSlot = availableSlot;
    }

    public DateModel(String date, String dayName, String dateId, Integer availableSlot) {
        this.date = date;
        this.dayName = dayName;
        this.dateId = dateId;
        this.availableSlot = availableSlot;
    }
}
