package com.example.vyapaar.ui.main.admin;

import com.example.vyapaar.BR;
import com.example.vyapaar.ui.model.RegistrationRequest;
import com.example.vyapaar.ui.model.RegistrationResponse;
import com.example.vyapaar.ui.network.RegistrationRetrofitManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.RawRes;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.observers.DisposableObserver;
import rx.Subscriber;

public class AdminRegistrationViewModel extends ViewModel implements Observable {
    public ObservableField<String> date = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> phNo = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    private DisposableObserver<RegistrationResponse> registrationResponseSubscriber;
    public MutableLiveData<RegistrationResponse> mutableLiveData = new MutableLiveData<RegistrationResponse>();


    public void updateLabel(Calendar myCalendar) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date.set(sdf.format(myCalendar.getTime()));
    }

    public void submitRegistration() {
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
                mutableLiveData.setValue(registrationResponse);
            }

            @Override
            public void onError(Throwable e) {
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


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        name.addOnPropertyChangedCallback(propertyChangedCallback);
        password.addOnPropertyChangedCallback(propertyChangedCallback);
        email.addOnPropertyChangedCallback(propertyChangedCallback);
        phNo.addOnPropertyChangedCallback(propertyChangedCallback);
        date.addOnPropertyChangedCallback(propertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        password.removeOnPropertyChangedCallback(propertyChangedCallback);
        name.removeOnPropertyChangedCallback(propertyChangedCallback);
        email.removeOnPropertyChangedCallback(propertyChangedCallback);
        phNo.removeOnPropertyChangedCallback(propertyChangedCallback);
        date.removeOnPropertyChangedCallback(propertyChangedCallback);

    }

    private OnPropertyChangedCallback propertyChangedCallback = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
        }
    };

    public void unsubscribe(){
        registrationResponseSubscriber.dispose();
    }

}
