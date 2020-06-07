package com.example.vyapaar.ui.network;


import android.util.Log;

import com.example.vyapaar.ui.model.RegistrationRequest;
import com.example.vyapaar.ui.model.RegistrationResponse;
import com.google.gson.Gson;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

public class RegistrationRetrofitManager extends RetrofitManager {

    public static Observable<RegistrationResponse> postRegistrationData(RegistrationRequest registrationRequest, Subscriber<RegistrationResponse> registrationResponseSubscriber) {
        String formData = new Gson().toJson(registrationRequest);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("data",formData).build();
        Observable<RegistrationResponse> responseObservable = null;
        RegistrationServiceInterface registrationServiceInterface = RetrofitManager.getRetrofitInstance().
                create(RegistrationServiceInterface.class);
        responseObservable = registrationServiceInterface.postUserData(body);
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistrationResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegistrationResponse registrationResponse) {
                        Log.i("--Rakesh---","SUCCESS");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("--Rakesh---","ERROR");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return responseObservable;
    }

}
