package com.example.vyapaar.login.main.admin;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
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
import com.example.vyapaar.login.contract.RegistrationContract;
import com.example.vyapaar.login.model.RegistrationResponse;

import java.util.Calendar;

public class AdminRegistrationFragment extends Fragment {
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
                    callback.launchOTPFragment(registrationResponse);
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
        mViewModel.isLoaderShown.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(mViewModel.isLoaderShown.get()){
                    callback.showLoader();
                }else{
                    callback.hideLoader();
                }
            }
        });
        AdminRegistrationFragmentBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(mViewModel);
        binding.setCallback(callback);
        return view;
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

    public AdminRegistrationViewModel getmViewModel(){
            return mViewModel;
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
