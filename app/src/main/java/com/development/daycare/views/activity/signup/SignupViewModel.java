package com.development.daycare.views.activity.signup;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.homeModel.HomeApiResponse;
import com.development.daycare.model.signupModel.SignUpApiResponse;
import com.development.daycare.model.signupModel.UserSignupRequest;
import com.development.daycare.views.fragment.home.HomeRepository;

import java.util.Map;

public class SignupViewModel extends AndroidViewModel {

    public SignupViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<SignUpApiResponse> setSignUp(Context context, Map<String,String> headers, UserSignupRequest request) {
        return SigupRepository.getInstance().setSignUp(context, headers, request);
    }
}
