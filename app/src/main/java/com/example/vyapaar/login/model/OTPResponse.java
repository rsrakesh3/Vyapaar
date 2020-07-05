package com.example.vyapaar.login.model;

import com.google.gson.annotations.SerializedName;

public class OTPResponse {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
