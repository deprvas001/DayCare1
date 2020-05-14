package com.development.daycare.views.activity.partnerProfile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.partnerprofile.ProfileApiResponse;
import com.development.daycare.model.partnerprofile.ProfileData;
import com.development.daycare.model.partnerprofile.ProfileResponse;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnerProfileRepository {

    private static PartnerProfileRepository profileRepository = null;
    private ShipmentApi shipmentApi;

    public PartnerProfileRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static PartnerProfileRepository getInstance(){
        if(profileRepository == null)
            profileRepository =new PartnerProfileRepository();
        return profileRepository;
    }

    public MutableLiveData<ProfileApiResponse> getProfile(Context context, Map<String,String> headers){
        final MutableLiveData<ProfileApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.getProfile(headers).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        profileResponseLiveData.setValue(new ProfileApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new ProfileApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new ProfileApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }

    public MutableLiveData<ProfileApiResponse> updateProfile(Context context, Map<String,String> headers, ProfileData profile){
        final MutableLiveData<ProfileApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.updateProfile(headers,profile).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        profileResponseLiveData.setValue(new ProfileApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new ProfileApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new ProfileApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }
}
