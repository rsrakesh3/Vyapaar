package com.example.vyapaar.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeoDetails {
    @SerializedName("geonames")
    @Expose
    private List<Geoname> geonames = null;

    public List<Geoname> getGeonames() {
        return geonames;
    }

    public void setGeonames(List<Geoname> geonames) {
        this.geonames = geonames;
    }
}
