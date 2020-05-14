package com.development.daycare.views.activity.dayCareAdd;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.addDay.AddCareApiResponse;
import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.model.addDay.AddCareResponse;
import com.development.daycare.model.loginModel.LoginRequest;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDayRepository {

    private static AddDayRepository dayCareRepository = null;
    private ShipmentApi shipmentApi;

    public AddDayRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static AddDayRepository getInstance(){
        if(dayCareRepository == null)
            dayCareRepository =new AddDayRepository();
        return dayCareRepository;
    }

    public MutableLiveData<AddCareApiResponse> addDayCare(Context context, Map<String,String> headers, AddCareRequest request){
        final MutableLiveData<AddCareApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.addDayCare(headers,request).enqueue(new Callback<AddCareResponse>() {
            @Override
            public void onResponse(Call<AddCareResponse> call, Response<AddCareResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new AddCareApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new AddCareApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<AddCareResponse> call, Throwable t) {
                responseLiveData.setValue(new AddCareApiResponse(t));
            }
        });

        return   responseLiveData;
    }
}
