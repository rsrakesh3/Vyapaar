package com.example.vyapaar.ui.views;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.databinding.BindingAdapter;

public class CustomEditText extends androidx.appcompat.widget.AppCompatEditText {
    public CustomEditText(Context context) {
        super(context);
    }



    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindingAdapter("onTextChangeListener")
    public static void setOnTextChangeListener(CustomEditText editText, TextWatcher textWatcher){
        editText.addTextChangedListener(textWatcher);
    }

}
