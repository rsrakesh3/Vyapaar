package com.example.vyapaar.ui.main;

import com.example.vyapaar.BR;
import com.example.vyapaar.ui.model.RegistrationRequest;
import com.example.vyapaar.ui.network.RegistrationRetrofitManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class AdminRegistrationViewModel extends ViewModel implements Observable {
    public ObservableField<String> date = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> phNo = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");


    public String updateLabel(Calendar myCalendar) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        return sdf.format(myCalendar.getTime());
    }

    public void submitRegistration(){
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setName(name.get());
        registrationRequest.setEmailid(email.get());
        registrationRequest.setPhNo(phNo.get());
        registrationRequest.setPwd(password.get());
        registrationRequest.setDob(date.get());
        RegistrationRetrofitManager.postRegistrationData(registrationRequest);
    }

    @Bindable
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
        this.password.notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
        this.email.notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
        this.name.notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getPhone() {
        return phNo.get();
    }

    public void setPhone(String phone) {
        this.phNo.set(phone);
        this.phNo.notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getDateOfBirth() {
        return date.get();
    }

    public void setDOB(String dateOfBirth) {
        this.date.set(dateOfBirth);
        this.date.notifyPropertyChanged(BR.dateOfBirth);
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        name.addOnPropertyChangedCallback(passwordProperyChangeCallbak);
        password.addOnPropertyChangedCallback(passwordProperyChangeCallbak);

        email.addOnPropertyChangedCallback(passwordProperyChangeCallbak);
        phNo.addOnPropertyChangedCallback(passwordProperyChangeCallbak);
        date.addOnPropertyChangedCallback(passwordProperyChangeCallbak);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        password.removeOnPropertyChangedCallback(passwordProperyChangeCallbak);
        name.removeOnPropertyChangedCallback(passwordProperyChangeCallbak);
        email.removeOnPropertyChangedCallback(passwordProperyChangeCallbak);
        phNo.removeOnPropertyChangedCallback(passwordProperyChangeCallbak);
        date.removeOnPropertyChangedCallback(passwordProperyChangeCallbak);

    }

    private OnPropertyChangedCallback passwordProperyChangeCallbak = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
//            password.set(sender.toString());
        }
    };
}