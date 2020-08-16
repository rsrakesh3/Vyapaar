package com.example.vyapaar.login.views;

import android.content.Context;
import android.util.AttributeSet;

import java.util.List;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;

public class CustomSpinner extends AppCompatSpinner {
    public CustomSpinner(Context context) {
        super(context);
    }

    public CustomSpinner(Context context, int mode) {
        super(context, mode);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*@BindingAdapter("entries")
    public void setEntries(List<String> entries) {
        setSpinnerEntries(entries);
    }

    @BindingAdapter("onItemSelected")
    public void setItemSelectedListener(ItemSelectedListener itemSelectedListener) {
        setSpinnerItemSelectedListener(itemSelectedListener);
    }*/

    /*@BindingAdapter("newValue")
    public void setNewValue() {
        setSpinnerValue(newValue);
    }*/
}
