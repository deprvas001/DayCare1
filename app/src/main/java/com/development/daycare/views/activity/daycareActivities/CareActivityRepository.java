package com.development.daycare.views.activity.daycareActivities;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.addCareActivity.ActivityListApiResponse;
import com.development.daycare.model.addCareActivity.ActivityListResponse;
import com.development.daycare.model.addCareActivity.AddActivityApiResponse;
import com.development.daycare.model.addCareActivity.AddActivityRequest;
import com.development.daycare.model.addCareActivity.AddActivityResponse;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CareActivityRepository {
    private static CareActivityRepository dayCareRepository = null;
    private ShipmentApi shipmentApi;

    public CareActivityRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static CareActivityRepository getInstance(){
        if(dayCareRepository == null)
            dayCareRepository =new CareActivityRepository();
        return dayCareRepository;
    }

    public MutableLiveData<AddActivityApiResponse> addActivity(Context context, Map<String,String> headers, AddActivityRequest request){
        final MutableLiveData<AddActivityApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.addActivity(headers,request).enqueue(new Callback<AddActivityResponse>() {
            @Override
            public void onResponse(Call<AddActivityResponse> call, Response<AddActivityResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new AddActivityApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new AddActivityApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<AddActivityResponse> call, Throwable t) {
                responseLiveData.setValue(new AddActivityApiResponse(t));
            }
        });

        return   responseLiveData;
    }


    public MutableLiveData<ActivityListApiResponse> getActivityList(Context context, Map<String,String> headers, String off_set, String page, String id){
        final MutableLiveData<ActivityListApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.getActivityList(headers,off_set,page,id).enqueue(new Callback<ActivityListResponse>() {
            @Override
            public void onResponse(Call<ActivityListResponse> call, Response<ActivityListResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new ActivityListApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new ActivityListApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ActivityListResponse> call, Throwable t) {
                responseLiveData.setValue(new ActivityListApiResponse(t));
            }
        });

        return   responseLiveData;
    }
}
