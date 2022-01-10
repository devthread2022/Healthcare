package com.jvt.devthread.healthcare.Activities.Model;

public class ConcernIssuesModel {
    String concernId,concernName,icon;

    public ConcernIssuesModel() {
    }

    public String getConcernId() {
        return concernId;
    }

    public void setConcernId(String concernId) {
        this.concernId = concernId;
    }

    public String getConcernName() {
        return concernName;
    }

    public void setConcernName(String concernName) {
        this.concernName = concernName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ConcernIssuesModel(String concernId, String concernName, String icon) {
        this.concernId = concernId;
        this.concernName = concernName;
        this.icon = icon;
    }
}
