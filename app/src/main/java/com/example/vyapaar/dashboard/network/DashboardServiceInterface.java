package com.example.vyapaar.dashboard.network;

import com.example.vyapaar.dashboard.model.DailyPrediction;
import com.example.vyapaar.dashboard.model.DashboardResponse;

import androidx.databinding.ObservableArrayList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DashboardServiceInterface {

    @GET("/api/v1/servicelist")
    Observable<DashboardResponse> getData();

    @POST("v1/daily_nakshatra_prediction")
    Observable<DailyPrediction> getDailyPrediction();
}
