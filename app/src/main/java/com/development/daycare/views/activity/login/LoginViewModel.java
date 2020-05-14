package com.development.daycare.views.activity.login;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.loginModel.LoginApiResponse;
import com.development.daycare.model.loginModel.LoginRequest;
import com.development.daycare.model.signupModel.SignUpApiResponse;
import com.development.daycare.model.signupModel.UserSignupRequest;
import com.development.daycare.views.activity.signup.SigupRepository;

import java.util.Map;

public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<LoginApiResponse> login(Context context, Map<String,String> headers, LoginRequest request) {
        return LoginRepository.getInstance().login(context, headers, request);
    }
}
