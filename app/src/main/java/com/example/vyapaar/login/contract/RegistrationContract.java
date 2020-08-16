package com.example.vyapaar.login.contract;

import com.example.vyapaar.login.model.RegistrationResponse;

public interface RegistrationContract {
    void openDatePicker();
    void openTimePicker();
    void launchLoginFragment();
    void launchOTPFragment(RegistrationResponse registrationResponse);
    void showLoader();
    void hideLoader();
}
