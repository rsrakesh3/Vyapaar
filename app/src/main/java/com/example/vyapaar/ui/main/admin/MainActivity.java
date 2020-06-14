package com.example.vyapaar.ui.main.admin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.vyapaar.R;
import com.example.vyapaar.ui.contract.LoginContract;
import com.example.vyapaar.ui.contract.RegistrationContract;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements RegistrationContract, LoginContract {
    final Calendar myCalendar = Calendar.getInstance();
    private AdminRegistrationFragment adminRegistrationFragment;
    private AdminLoginFragment adminLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
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
    public void launchLoginFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AdminLoginFragment.newInstance())
                .commit();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            if(adminRegistrationFragment!=null && adminRegistrationFragment.getmViewModel()!=null){
                adminRegistrationFragment.getmViewModel().updateLabel(myCalendar);
            }
        }

    };

    @Override
    public void launchRegistrationFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AdminRegistrationFragment.newInstance())
                .commitNow();
    }
}
