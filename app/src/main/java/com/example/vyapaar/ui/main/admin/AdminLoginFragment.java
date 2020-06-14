package com.example.vyapaar.ui.main.admin;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vyapaar.R;
import com.example.vyapaar.databinding.AdminLoginFragmentBinding;
import com.example.vyapaar.ui.contract.LoginContract;

public class AdminLoginFragment extends Fragment {

    private AdminLoginViewModel mViewModel;
    private Context mContext;
    private LoginContract loginContract;

    public static AdminLoginFragment newInstance() {
        return new AdminLoginFragment();
    }

    public void setContract(LoginContract contract){
        loginContract = contract;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_login_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(AdminLoginViewModel.class);
        AdminLoginFragmentBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(mViewModel);
        binding.setCallback(loginContract);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.unsubscribe();
    }
}
