package com.jvt.devthread.healthcare.Activities.Model;

public class LabTestModel {
    String testId,testName,info;
    Long price;

    public LabTestModel() {
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LabTestModel(String testId, String testName, String info, Long price) {
        this.testId = testId;
        this.testName = testName;
        this.info = info;
        this.price = price;
    }
}
