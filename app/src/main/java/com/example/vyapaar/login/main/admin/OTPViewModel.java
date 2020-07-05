package com.example.vyapaar.login.main.admin;

import com.example.vyapaar.login.contract.OTPContract;
import com.example.vyapaar.login.model.OTPResponse;
import com.example.vyapaar.login.model.RegistrationResponse;
import com.example.vyapaar.login.network.RegistrationRetrofitManager;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.observers.DisposableObserver;

public class OTPViewModel extends ViewModel {
    public ObservableBoolean isBtnEnabled = new ObservableBoolean(false);
    public ObservableField<String> pin1 = new ObservableField<>("0");
    public ObservableField<String> pin2 = new ObservableField<>("0");
    public ObservableField<String> pin3 = new ObservableField<>("0");
    public ObservableField<String> pin4 = new ObservableField<>("0");
    private DisposableObserver<OTPResponse> otpResponseDisposable;
    public MutableLiveData<OTPResponse> mutableLiveData = new MutableLiveData<OTPResponse>();
    public ObservableBoolean isLoaderShown = new ObservableBoolean(false);

    public void verifyBtnClick(OTPContract contract){
        StringBuilder builder = new StringBuilder(pin1.get()+pin2.get()+pin3.get()+pin4.get());
        contract.handleOTPSuccess(builder.toString());
    }

    public void postOTPData(String otp) {
        isLoaderShown.set(true);
        RegistrationRetrofitManager.postOTP(otp, otpResponseDisposableObserver());
    }

    private DisposableObserver<OTPResponse> otpResponseDisposableObserver(){
        otpResponseDisposable = new DisposableObserver<OTPResponse>() {
            @Override
            public void onNext(OTPResponse otpResponse) {
                isLoaderShown.set(false);
                mutableLiveData.setValue(otpResponse);
            }

            @Override
            public void onError(Throwable e) {
                isLoaderShown.set(false);
                mutableLiveData.setValue(null);
            }

            @Override
            public void onComplete() {

            }
        };
        return otpResponseDisposable;
    }
}
