package com.example.vyapaar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.vyapaar.ui.contract.RegistrationContract;
import com.example.vyapaar.ui.main.AdminLoginFragment;
import com.example.vyapaar.ui.main.AdminRegistrationFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements RegistrationContract {
    final Calendar myCalendar = Calendar.getInstance();

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
            ((AdminRegistrationFragment) fragment).setContract(this);
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
                .addToBackStack(null)
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
//            mViewModel.updateLabel(myCalendar);
        }

    };
}
