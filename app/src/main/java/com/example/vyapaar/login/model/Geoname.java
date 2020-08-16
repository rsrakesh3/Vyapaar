package com.example.vyapaar.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geoname {
    @SerializedName("place_name")
    @Expose
    private String placeName;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("timezone_id")
    @Expose
    private String timezoneId;
    @SerializedName("country_code")
    @Expose
    private String countryCode;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
