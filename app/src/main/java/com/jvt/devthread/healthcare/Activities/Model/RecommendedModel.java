package com.jvt.devthread.healthcare.Activities.Model;

public class RecommendedModel {
    String heading,icon,info;

    public RecommendedModel() {
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public RecommendedModel(String heading, String icon, String info) {
        this.heading = heading;
        this.icon = icon;
        this.info = info;
    }
}
