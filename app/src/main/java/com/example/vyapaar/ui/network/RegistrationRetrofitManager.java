package com.example.vyapaar.ui.network;


import android.util.Log;

import com.example.vyapaar.ui.model.RegistrationRequest;
import com.example.vyapaar.ui.model.RegistrationResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrationRetrofitManager extends RetrofitManager {

    public static Observable<RegistrationResponse> postRegistrationData(RegistrationRequest registrationRequest) {
        Observable<RegistrationResponse> responseObservable = null;
        RegistrationServiceInterface registrationServiceInterface = RetrofitManager.getRetrofitInstance().
                create(RegistrationServiceInterface.class);
        responseObservable = registrationServiceInterface.postUserData(registrationRequest);
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
