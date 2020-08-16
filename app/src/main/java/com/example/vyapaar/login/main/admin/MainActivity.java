package com.example.vyapaar.login.main.admin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vyapaar.R;
import com.example.vyapaar.common.BaseActivity;
import com.example.vyapaar.dashboard.ui.NewDashboardActivity;
import com.example.vyapaar.login.contract.LoginContract;
import com.example.vyapaar.login.contract.RegistrationContract;
import com.example.vyapaar.login.model.RegistrationResponse;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

public class MainActivity extends BaseActivity implements RegistrationContract, LoginContract {
    final Calendar myCalendar = Calendar.getInstance();
    private AdminRegistrationFragment adminRegistrationFragment;
    private AdminLoginFragment adminLoginFragment;
    private LottieAnimationView mLoaderAnimation;
    private View mPageLevelLoaderView;
    private AppCompatTextView mPageLevelLoadingTextView;
    private boolean allowBackPress = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mLoaderAnimation = findViewById(R.id.page_level_progress_bar);
        setUpLoaderViews();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AdminRegistrationFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment instanceof AdminRegistrationFragment){
            adminRegistrationFragment = (AdminRegistrationFragment)fragment;
            ((AdminRegistrationFragment) fragment).setContract(this);
        }
        if(fragment instanceof AdminLoginFragment){
            adminLoginFragment = (AdminLoginFragment)fragment;
            ((AdminLoginFragment) fragment).setContract(this);
        }
    }

    @Override
    public void openDatePicker() {
        new DatePickerDialog(MainActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void openTimePicker() {
        new TimePickerDialog(MainActivity.this, timeSetListener,
                myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
    }

    @Override
    public void launchLoginFragment() {
        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AdminLoginFragment.newInstance())
                .commit();*/
        startActivity(new Intent(this, NewDashboardActivity.class));
    }

    @Override
    public void launchOTPFragment(RegistrationResponse registrationResponse) {
        OTPFragment otpFragment = OTPFragment.newInstance(registrationResponse);
        otpFragment.setCancelable(false);
        otpFragment.show(getSupportFragmentManager(), "otp_dialog");
    }

    @Override
    public void showLoader() {
        startAnimation();
    }

    @Override
    public void hideLoader() {
        stopAnimation();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            if(adminRegistrationFragment!=null && adminRegistrationFragment.getmViewModel()!=null){
                adminRegistrationFragment.getmViewModel().updateLabel(myCalendar);
            }
        }

    };

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            if(adminRegistrationFragment!=null && adminRegistrationFragment.getmViewModel()!=null){
                adminRegistrationFragment.getmViewModel().updateTimeLabel(myCalendar);
            }
        }
    };

    @Override
    public void launchRegistrationFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AdminRegistrationFragment.newInstance())
                .commitNow();
    }

    public void startAnimation(){
        allowBackPress = false;
        mLoaderAnimation.playAnimation();
        mPageLevelLoaderView.setVisibility(View.VISIBLE);
    }

    public void stopAnimation(){
        allowBackPress = true;
        mLoaderAnimation.pauseAnimation();
        mPageLevelLoaderView.setVisibility(View.GONE);
    }

    protected void setUpLoaderViews() {
        if (mPageLevelLoaderView == null || mLoaderAnimation == null) {
            mPageLevelLoaderView = findViewById(R.id.page_level_loader_view);
            if (mPageLevelLoaderView != null)
                mPageLevelLoadingTextView = mPageLevelLoaderView.findViewById(R.id.loading_text_view);
            mLoaderAnimation = findViewById(R.id.page_level_progress_bar);
        }
    }
}
