package com.jvt.devthread.healthcare.Activities.Model;

public class PersonalDetailModel {
    String userName, profile, contact, email, gender, dob, bloodGroup, maritalStatus, height, weight, emergencyContact, location;


    public PersonalDetailModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PersonalDetailModel(String userName, String profile, String contact, String email, String gender, String dob, String bloodGroup, String maritalStatus, String height, String weight, String emergencyContact, String location) {
        this.userName = userName;
        this.profile = profile;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.bloodGroup = bloodGroup;
        this.maritalStatus = maritalStatus;
        this.height = height;
        this.weight = weight;
        this.emergencyContact = emergencyContact;
        this.location = location;
    }
}
