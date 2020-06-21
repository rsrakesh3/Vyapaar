package com.example.vyapaar.dashboard.network;

import com.example.vyapaar.dashboard.model.DashboardResponse;
import com.example.vyapaar.ui.model.RegistrationResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DashboardServiceInterface {

    @GET("/api/v1/servicelist")
    Observable<DashboardResponse> getData();
}
