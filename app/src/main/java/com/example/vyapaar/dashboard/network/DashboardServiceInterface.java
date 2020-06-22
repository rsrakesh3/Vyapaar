package com.example.vyapaar.dashboard.network;

import com.example.vyapaar.dashboard.model.DashboardResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DashboardServiceInterface {

    @GET("/api/v1/servicelist")
    Observable<DashboardResponse> getData();
}
