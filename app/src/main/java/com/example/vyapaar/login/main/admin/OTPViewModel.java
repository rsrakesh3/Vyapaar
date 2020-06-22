package com.example.vyapaar.login.main.admin;

import com.example.vyapaar.login.contract.OTPContract;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class OTPViewModel extends ViewModel {
    public ObservableBoolean isBtnEnabled = new ObservableBoolean(false);
    public ObservableField<String> pin1 = new ObservableField<>("0");
    public ObservableField<String> pin2 = new ObservableField<>("0");
    public ObservableField<String> pin3 = new ObservableField<>("0");
    public ObservableField<String> pin4 = new ObservableField<>("0");

    public void verifyBtnClick(OTPContract contract){
        StringBuilder builder = new StringBuilder(pin1.get()+pin2.get()+pin3.get()+pin4.get());
        contract.handleOTPSuccess(builder.toString());
    }
}
