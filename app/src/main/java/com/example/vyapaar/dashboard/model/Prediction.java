package com.example.vyapaar.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction {
    @SerializedName("health")
    @Expose
    private String health;
    @SerializedName("emotions")
    @Expose
    private String emotions;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("luck")
    @Expose
    private String luck;
    @SerializedName("personal_life")
    @Expose
    private String personalLife;
    @SerializedName("travel")
    @Expose
    private String travel;

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getEmotions() {
        return emotions;
    }

    public void setEmotions(String emotions) {
        this.emotions = emotions;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLuck() {
        return luck;
    }

    public void setLuck(String luck) {
        this.luck = luck;
    }

    public String getPersonalLife() {
        return personalLife;
    }

    public void setPersonalLife(String personalLife) {
        this.personalLife = personalLife;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }
}
