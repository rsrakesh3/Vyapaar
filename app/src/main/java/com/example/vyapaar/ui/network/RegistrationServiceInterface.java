package com.example.vyapaar.ui.network;

import com.example.vyapaar.ui.model.RegistrationRequest;
import com.example.vyapaar.ui.model.RegistrationResponse;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegistrationServiceInterface {

    @POST("/customer")
    Observable<RegistrationResponse> postUserData(@Body RegistrationRequest registrationRequest);
}