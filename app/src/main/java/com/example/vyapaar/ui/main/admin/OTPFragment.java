package com.example.vyapaar.ui.main.admin;

import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vyapaar.R;
import com.example.vyapaar.databinding.OtpFragmentBinding;
import com.example.vyapaar.ui.contract.OTPContract;
import com.example.vyapaar.ui.model.RegistrationResponse;

public class OTPFragment extends DialogFragment implements OTPContract {

    private OTPViewModel mViewModel;
    private EditText editText1, editText2, editText3, editText4;
    private static RegistrationResponse mResponse;
    private OTPContract otpContract;
    private Context mContext;
    private AppCompatButton verifyBtn;

    public static OTPFragment newInstance(RegistrationResponse registrationResponse) {
        mResponse = registrationResponse;
        return new OTPFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.otp_fragment, container, false);
        OtpFragmentBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        binding.setCallback(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        editText1 = view.findViewById(R.id.edittext1);
        editText1.requestFocus();
        editText2 = view.findViewById(R.id.edittext2);
        editText3 = view.findViewById(R.id.edittext3);
        editText4 = view.findViewById(R.id.edittext4);
        verifyBtn = view.findViewById(R.id.btn_verify);
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Success",Toast.LENGTH_LONG).show();
            }
        });
        editText1.addTextChangedListener(new GenericTextWatcher(editText1));
        editText2.addTextChangedListener(new GenericTextWatcher(editText2));
        editText3.addTextChangedListener(new GenericTextWatcher(editText3));
        editText4.addTextChangedListener(new GenericTextWatcher(editText4));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OTPViewModel.class);

    }

    @Override
    public void handleOTPSuccess(String s) {
        if(mResponse.getOtp().equalsIgnoreCase(s)){
            Toast.makeText(mContext, "Success",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void handleOTPError() {

    }

    public class GenericTextWatcher implements TextWatcher
    {
        private View view;
        private GenericTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch(view.getId())
            {

                case R.id.edittext1:
                    if(text.length()==1)
                        editText2.requestFocus();
                    break;
                case R.id.edittext2:
                    if(text.length()==1) {
                        editText3.requestFocus();
                    }else if(text.length()==0){
                        editText1.requestFocus();
                    }
                    break;
                case R.id.edittext3:
                    if(text.length()==1) {
                        editText4.requestFocus();
                    }else if(text.length()==0){
                        editText2.requestFocus();
                    }
                    break;
                case R.id.edittext4:
                    if(text.length()==1){
                        mViewModel.isBtnEnabled.set(true);
                        verifyBtn.requestFocus();
                    }else if(text.length()==0){
                        editText3.requestFocus();
                    }
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

    }

}
