package com.development.daycare.views.activity.dayCareAdd;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.addDay.AddCareApiResponse;
import com.development.daycare.model.addDay.AddCareRequest;;
import com.development.daycare.model.loginModel.LoginRequest;
import com.development.daycare.views.activity.login.LoginRepository;

import java.util.Map;

public class AddCareViewModel extends AndroidViewModel {

    public AddCareViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<AddCareApiResponse> addDayCare(Context context, Map<String,String> headers, AddCareRequest request) {
        return AddDayRepository.getInstance().addDayCare(context, headers, request);
    }
}
