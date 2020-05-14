package com.development.daycare.views.activity.partnerProfile;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.partnerprofile.ProfileApiResponse;
import com.development.daycare.model.partnerprofile.ProfileData;

import java.util.Map;

public class PartnerProfileViewModel extends AndroidViewModel {

    public PartnerProfileViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<ProfileApiResponse> getProfile(Context context, Map<String,String> headers ) {
        return PartnerProfileRepository.getInstance().getProfile(context, headers);
    }

    public MutableLiveData<ProfileApiResponse> updateProfile(Context context, Map<String,String> headers, ProfileData profile) {
        return PartnerProfileRepository.getInstance().updateProfile(context, headers,profile);
    }
}
