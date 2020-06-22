package com.example.vyapaar.login.network;

import com.example.vyapaar.login.model.RegistrationResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrationServiceInterface {

    @POST("/api/v1/cust/register")
    Observable<RegistrationResponse> postUserData(@Body RequestBody params);
}
