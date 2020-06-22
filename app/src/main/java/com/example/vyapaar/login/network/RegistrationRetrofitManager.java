package com.example.vyapaar.login.network;


import com.example.vyapaar.login.model.RegistrationRequest;
import com.example.vyapaar.login.model.RegistrationResponse;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegistrationRetrofitManager extends RetrofitManager {

    public static Observable<RegistrationResponse> postRegistrationData(RegistrationRequest registrationRequest, DisposableObserver<RegistrationResponse> registrationResponseSubscriber) {
        String formData = new Gson().toJson(registrationRequest);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("data",formData).build();
        Observable<RegistrationResponse> responseObservable = null;
        RegistrationServiceInterface registrationServiceInterface = RetrofitManager.getRetrofitInstance().
                create(RegistrationServiceInterface.class);
        responseObservable = registrationServiceInterface.postUserData(body);
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(registrationResponseSubscriber);
        return responseObservable;
    }

}
