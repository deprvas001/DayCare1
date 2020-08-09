package com.development.daycare.views.activity.changePassword;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.model.changePassword.ChangePasswordRequest;
import com.development.daycare.model.changePassword.PasswordApiResponse;
import com.development.daycare.views.activity.dayCareAdd.AddDayRepository;

import java.util.Map;

public class PasswordViewModel extends AndroidViewModel {
    public PasswordViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<PasswordApiResponse> changePassword(Context context, Map<String,String> headers, ChangePasswordRequest request) {
        return ChangePasswordRepository.getInstance().changePassword(context, headers, request);
    }
}
