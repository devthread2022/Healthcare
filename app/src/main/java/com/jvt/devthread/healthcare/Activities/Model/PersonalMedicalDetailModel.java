package com.jvt.devthread.healthcare.Activities.Model;

public class PersonalMedicalDetailModel {
    String allergies, currentMedication, pastMedication, chronicDisease, injuries, surgeries;

    public PersonalMedicalDetailModel() {
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public void setCurrentMedication(String currentMedication) {
        this.currentMedication = currentMedication;
    }

    public String getPastMedication() {
        return pastMedication;
    }

    public void setPastMedication(String pastMedication) {
        this.pastMedication = pastMedication;
    }

    public String getChronicDisease() {
        return chronicDisease;
    }

    public void setChronicDisease(String chronicDisease) {
        this.chronicDisease = chronicDisease;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public String getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(String surgeries) {
        this.surgeries = surgeries;
    }

    public PersonalMedicalDetailModel(String allergies, String currentMedication, String pastMedication, String chronicDisease, String injuries, String surgeries) {
        this.allergies = allergies;
        this.currentMedication = currentMedication;
        this.pastMedication = pastMedication;
        this.chronicDisease = chronicDisease;
        this.injuries = injuries;
        this.surgeries = surgeries;
    }
}
