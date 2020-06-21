package com.example.vyapaar.dashboard.ui.dashboard;

import com.example.vyapaar.dashboard.adapter.DashboardItemRecyclerViewAdapter;
import com.example.vyapaar.dashboard.model.DashboardResponse;
import com.example.vyapaar.dashboard.network.DashboardRetrofitManager;
import com.example.vyapaar.ui.model.RegistrationResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.observers.DisposableObserver;

public class DashboardViewModel extends ViewModel {
    private DashboardItemRecyclerViewAdapter itemAdapter;
    private List<String > data;
    public MutableLiveData<DashboardResponse> mutableLiveData = new MutableLiveData<DashboardResponse>();
    public ObservableBoolean isLoading = new ObservableBoolean(true);

    public DashboardViewModel(){
    }

    public void getDashboardData() {
        DashboardRetrofitManager.getDashboardData(new DisposableObserver<DashboardResponse>() {
            @Override
            public void onNext(DashboardResponse dashboardResponse) {
                isLoading.set(false);
                mutableLiveData.setValue(dashboardResponse);
            }

            @Override
            public void onError(Throwable e) {
                isLoading.set(false);
                data = new ArrayList<>();
                data.add("Dummy1");
                data.add("Dummy2");
                data.add("Dummy3");
                data.add("Dummy4");
                data.add("Dummy5");
                DashboardResponse response = new DashboardResponse();
                response.setData(data);
                mutableLiveData.setValue(response);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
