package com.jvt.devthread.healthcare.Activities.Model;

public class DoctorModel {
    String docId,docName, docSpecialization, docExperience, docEducation, docAddress,membership, registration, about,docImage;
    Long consultationFee;

    public DoctorModel() {
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocSpecialization() {
        return docSpecialization;
    }

    public void setDocSpecialization(String docSpecialization) {
        this.docSpecialization = docSpecialization;
    }

    public String getDocExperience() {
        return docExperience;
    }

    public void setDocExperience(String docExperience) {
        this.docExperience = docExperience;
    }

    public String getDocEducation() {
        return docEducation;
    }

    public void setDocEducation(String docEducation) {
        this.docEducation = docEducation;
    }

    public String getDocAddress() {
        return docAddress;
    }

    public void setDocAddress(String docAddress) {
        this.docAddress = docAddress;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDocImage() {
        return docImage;
    }

    public void setDocImage(String docImage) {
        this.docImage = docImage;
    }

    public Long getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Long consultationFee) {
        this.consultationFee = consultationFee;
    }

    public DoctorModel(String docId, String docName, String docSpecialization, String docExperience, String docEducation, String docAddress, String membership, String registration, String about, String docImage, Long consultationFee) {
        this.docId = docId;
        this.docName = docName;
        this.docSpecialization = docSpecialization;
        this.docExperience = docExperience;
        this.docEducation = docEducation;
        this.docAddress = docAddress;
        this.membership = membership;
        this.registration = registration;
        this.about = about;
        this.docImage = docImage;
        this.consultationFee = consultationFee;
    }
}
