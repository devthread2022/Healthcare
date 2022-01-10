package com.jvt.devthread.healthcare.Activities.Model;

public class PersonalLifestyleModel {
    String smokingHabit, alcoholConsumption, foodPreference, occupation;

    public PersonalLifestyleModel() {
    }

    public String getSmokingHabit() {
        return smokingHabit;
    }

    public void setSmokingHabit(String smokingHabit) {
        this.smokingHabit = smokingHabit;
    }

    public String getAlcoholConsumption() {
        return alcoholConsumption;
    }

    public void setAlcoholConsumption(String alcoholConsumption) {
        this.alcoholConsumption = alcoholConsumption;
    }

    public String getFoodPreference() {
        return foodPreference;
    }

    public void setFoodPreference(String foodPreference) {
        this.foodPreference = foodPreference;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public PersonalLifestyleModel(String smokingHabit, String alcoholConsumption, String foodPreference, String occupation) {
        this.smokingHabit = smokingHabit;
        this.alcoholConsumption = alcoholConsumption;
        this.foodPreference = foodPreference;
        this.occupation = occupation;
    }
}
