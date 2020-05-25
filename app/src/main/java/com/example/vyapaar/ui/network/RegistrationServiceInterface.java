package com.example.vyapaar.ui.network;

import com.example.vyapaar.ui.model.RegistrationResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegistrationServiceInterface {

    @POST("/api/v1/cust/register")
    @FormUrlEncoded
    Observable<RegistrationResponse> postUserData(@FieldMap HashMap<String, String> params);
}
