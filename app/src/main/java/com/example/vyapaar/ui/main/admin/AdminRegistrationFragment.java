package com.example.vyapaar.ui.main.admin;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.vyapaar.R;
import com.example.vyapaar.databinding.AdminRegistrationFragmentBinding;
import com.example.vyapaar.ui.contract.RegistrationContract;
import com.example.vyapaar.ui.model.RegistrationResponse;

import java.util.Calendar;

public class AdminRegistrationFragment extends Fragment implements RegistrationContract {
    final Calendar myCalendar = Calendar.getInstance();
    private AdminRegistrationViewModel mViewModel;
    private RegistrationContract callback;
    private Context mContext;

    public static AdminRegistrationFragment newInstance() {
        return new AdminRegistrationFragment();
    }

    public void setContract(RegistrationContract contract){
        callback = contract;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<RegistrationResponse>() {
            @Override
            public void onChanged(RegistrationResponse registrationResponse) {
                if(registrationResponse.getOtp()!=null){
                    launchOTPFragment(registrationResponse);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_registration_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(AdminRegistrationViewModel.class);
        AdminRegistrationFragmentBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(mViewModel);
        binding.setCallback(callback);
        return view;
    }

    private void launchOTPFragment(RegistrationResponse registrationResponse) {
        OTPFragment otpFragment = OTPFragment.newInstance(registrationResponse);
        otpFragment.setCancelable(false);
        otpFragment.show(((MainActivity) mContext).getSupportFragmentManager(), "otp_dialog");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void openDatePicker() {
        new DatePickerDialog(mContext, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
     public AdminRegistrationViewModel getmViewModel(){
            return mViewModel;
    }

    @Override
    public void launchLoginFragment() {
        ((MainActivity) mContext).getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AdminLoginFragment.newInstance())
                .commitNow();
    }



    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            mViewModel.updateLabel(myCalendar);
        }

    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mViewModel!=null) {
            mViewModel.unsubscribe();
        }
    }
}