package com.example.vyapaar.ui.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

import com.example.vyapaar.R;

import androidx.appcompat.widget.AppCompatEditText;

public class OTPEditText extends AppCompatEditText {
    public OTPEditText(Context context) {
        super(context);
    }

    public OTPEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
//        setOnTextWatcher();
    }

    private void initUI() {

    }

    public OTPEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
