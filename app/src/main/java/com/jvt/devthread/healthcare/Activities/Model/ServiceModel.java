package com.jvt.devthread.healthcare.Activities.Model;

public class ServiceModel {
    String serviceName;

    public ServiceModel() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceModel(String serviceName) {
        this.serviceName = serviceName;
    }
}
