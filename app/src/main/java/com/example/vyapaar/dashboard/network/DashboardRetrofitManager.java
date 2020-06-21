package com.example.vyapaar.dashboard.network;

import com.example.vyapaar.dashboard.model.DashboardResponse;
import com.example.vyapaar.ui.network.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DashboardRetrofitManager extends RetrofitManager {

    public static Observable<DashboardResponse> getDashboardData(DisposableObserver<DashboardResponse> dashboardSubscriber) {

        Observable<DashboardResponse> responseObservable = null;
        DashboardServiceInterface dashboardServiceInterface = RetrofitManager.getRetrofitInstance().
                create(DashboardServiceInterface.class);
        responseObservable = dashboardServiceInterface.getData();
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(dashboardSubscriber);
        return responseObservable;
    }
}
