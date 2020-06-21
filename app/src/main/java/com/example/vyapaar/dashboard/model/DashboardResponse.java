package com.example.vyapaar.dashboard.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashboardResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<String> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
