package com.example.vyapaar.login.main.admin;

import android.app.Application;

import com.example.vyapaar.BR;
import com.example.vyapaar.common.CommonUtil;
import com.example.vyapaar.common.model.State;
import com.example.vyapaar.common.model.StateList;
import com.example.vyapaar.login.model.RegistrationRequest;
import com.example.vyapaar.login.model.RegistrationResponse;
import com.example.vyapaar.login.network.RegistrationRetrofitManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.ServiceLoader;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.observers.DisposableObserver;

public class AdminRegistrationViewModel extends AndroidViewModel implements Observable {
    public ObservableField<String> date = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> phNo = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    private DisposableObserver<RegistrationResponse> registrationResponseSubscriber;
    public MutableLiveData<RegistrationResponse> mutableLiveData = new MutableLiveData<RegistrationResponse>();
    public ObservableBoolean isLoaderShown = new ObservableBoolean(false);
    @Bindable
    public ObservableArrayList<State> entries = new ObservableArrayList<>();
    private Application application;
    private StateList stateLists;

    public AdminRegistrationViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        stateLists = CommonUtil.getStateList(application);
        entries.addAll(stateLists.getStates());
    }


    public void updateLabel(Calendar myCalendar) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date.set(sdf.format(myCalendar.getTime()));
    }

    public void submitRegistration() {
        isLoaderShown.set(true);
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setName(name.get());
        registrationRequest.setEmailid(email.get());
        registrationRequest.setPhNo(phNo.get());
        registrationRequest.setPwd(password.get());
        registrationRequest.setDob(date.get());
        RegistrationRetrofitManager.postRegistrationData(registrationRequest, registrationRequestSubscriber());
    }

    private DisposableObserver<RegistrationResponse> registrationRequestSubscriber() {
        registrationResponseSubscriber = new DisposableObserver<RegistrationResponse>() {
            @Override
            public void onNext(RegistrationResponse registrationResponse) {
                isLoaderShown.set(false);
                mutableLiveData.setValue(registrationResponse);
            }

            @Override
            public void onError(Throwable e) {
                isLoaderShown.set(false);
                RegistrationResponse response = new RegistrationResponse();
                response.setOtp("1234");
                mutableLiveData.setValue(response);
            }

            @Override
            public void onComplete() {

            }
        };
        return  registrationResponseSubscriber;
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

    @Bindable
    public String getTimeOfBirth(){
        return time.get();
    }

    public void setTimeOfBirth(String timeOfBirth){
        this.time.set(timeOfBirth);
        this.time.notifyPropertyChanged(BR.timeOfBirth);
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        name.addOnPropertyChangedCallback(propertyChangedCallback);
        password.addOnPropertyChangedCallback(propertyChangedCallback);
        email.addOnPropertyChangedCallback(propertyChangedCallback);
        phNo.addOnPropertyChangedCallback(propertyChangedCallback);
        date.addOnPropertyChangedCallback(propertyChangedCallback);
        time.addOnPropertyChangedCallback(propertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        password.removeOnPropertyChangedCallback(propertyChangedCallback);
        name.removeOnPropertyChangedCallback(propertyChangedCallback);
        email.removeOnPropertyChangedCallback(propertyChangedCallback);
        phNo.removeOnPropertyChangedCallback(propertyChangedCallback);
        date.removeOnPropertyChangedCallback(propertyChangedCallback);
        time.removeOnPropertyChangedCallback(propertyChangedCallback);

    }

    private OnPropertyChangedCallback propertyChangedCallback = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
        }
    };

    public void unsubscribe(){
        registrationResponseSubscriber.dispose();
    }

    public void updateTimeLabel(Calendar myCalendar) {
        String myFormat = "hh:mm aa"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        time.set(sdf.format(myCalendar.getTime()));
    }

   /* @BindingAdapter("entries")
    public void setEntries(){

    }*/

}
