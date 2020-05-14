package com.development.daycare.views.activity.daycareActivities;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.addCareActivity.ActivityListApiResponse;
import com.development.daycare.model.addCareActivity.AddActivityApiResponse;
import com.development.daycare.model.addCareActivity.AddActivityRequest;
import com.development.daycare.model.addDay.AddCareApiResponse;
import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.views.activity.dayCareAdd.AddDayRepository;

import java.util.Map;

public class CareViewModel extends AndroidViewModel {

    public CareViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<AddActivityApiResponse> addActivity(Context context, Map<String,String> headers, AddActivityRequest request) {
        return CareActivityRepository.getInstance().addActivity(context, headers, request);
    }

    public MutableLiveData<ActivityListApiResponse> getActivityList(Context context, Map<String,String> headers, String off_set,String page,String id) {
        return CareActivityRepository.getInstance().getActivityList(context, headers, off_set,page,id);
    }
}
