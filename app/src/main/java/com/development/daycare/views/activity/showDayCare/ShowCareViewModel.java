package com.development.daycare.views.activity.showDayCare;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.homeModel.HomeApiResponse;
import com.development.daycare.model.showCareModel.ShowCareApiResponse;
import com.development.daycare.views.fragment.home.HomeRepository;

import java.util.Map;

public class ShowCareViewModel extends AndroidViewModel {

    public ShowCareViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<ShowCareApiResponse> getCareList(Context context, Map<String,String> headers,String offset,String per_page) {
        return ShowCareRepository.getInstance().getCareList(context,headers,offset,per_page);
    }
}
