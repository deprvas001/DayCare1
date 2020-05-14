package com.development.daycare.views.activity.dayCareBanner;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.addBanner.AddBannerApiResponse;
import com.development.daycare.model.addBanner.AddBannerRequest;
import com.development.daycare.model.addBanner.BannerListApiResponse;
import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.views.activity.dayCareAdd.AddDayRepository;

import java.util.Map;

public class AddBannerViewModel  extends AndroidViewModel {

    public AddBannerViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<AddBannerApiResponse> addBanner(Context context, Map<String,String> headers, AddBannerRequest request) {
        return AddBannerRepository.getInstance().addBanner(context, headers, request);
    }

    public MutableLiveData<BannerListApiResponse> getBannerList(Context context, Map<String,String> headers, String offset, String type, String day_care_id) {
        return AddBannerRepository.getInstance().getBannerList(context, headers, offset,type,day_care_id);
    }
}
