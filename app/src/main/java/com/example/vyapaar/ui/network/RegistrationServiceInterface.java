package com.example.vyapaar.ui.network;

import com.example.vyapaar.ui.model.RegistrationResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RegistrationServiceInterface {

    @POST("/api/v1/cust/register")
    @Multipart
    Observable<RegistrationResponse> postUserData(@Part RequestBody params);
}
