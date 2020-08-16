package com.example.vyapaar.login.network;

import com.example.vyapaar.login.model.GeoDetails;
import com.example.vyapaar.login.model.OTPResponse;
import com.example.vyapaar.login.model.RegistrationResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegistrationServiceInterface {

    @POST("/api/v1/cust/register")
    Observable<RegistrationResponse> postUserData(@Body RequestBody params);

    @POST("/api/v1/user/activate")
    Observable<OTPResponse> validateOTP(@Body RequestBody params);

    /*@POST("/api/v1/user/activate")
    Observable<OTPResponse> postUserData(@Body RequestBody params);*/

    //https://api.vedicrishiastro.com/v1/geo_details
    @POST("/v1/geo_details")
    Observable<GeoDetails> getLatLong(@Body RequestBody params);
}
