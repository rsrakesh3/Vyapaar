package com.example.vyapaar.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyPrediction {
    @SerializedName("birth_moon_sign")
    @Expose
    private String birthMoonSign;
    @SerializedName("birth_moon_nakshatra")
    @Expose
    private String birthMoonNakshatra;
    @SerializedName("prediction")
    @Expose
    private Prediction prediction;
    @SerializedName("prediction_date")
    @Expose
    private String predictionDate;

    public String getBirthMoonSign() {
        return birthMoonSign;
    }

    public void setBirthMoonSign(String birthMoonSign) {
        this.birthMoonSign = birthMoonSign;
    }

    public String getBirthMoonNakshatra() {
        return birthMoonNakshatra;
    }

    public void setBirthMoonNakshatra(String birthMoonNakshatra) {
        this.birthMoonNakshatra = birthMoonNakshatra;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }

    public String getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(String predictionDate) {
        this.predictionDate = predictionDate;
    }
}
