package com.development.daycare.views.activity.dayCareBanner;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.addBanner.AddBannerApiResponse;
import com.development.daycare.model.addBanner.AddBannerRequest;
import com.development.daycare.model.addBanner.AddBannerResponse;
import com.development.daycare.model.addBanner.BannerListApiResponse;
import com.development.daycare.model.addBanner.BannerListResponse;
import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBannerRepository {
    private static AddBannerRepository bannerRepository = null;
    private ShipmentApi shipmentApi;

    public AddBannerRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static AddBannerRepository getInstance(){
        if(bannerRepository == null)
            bannerRepository =new AddBannerRepository();
        return bannerRepository;
    }

    public MutableLiveData<AddBannerApiResponse> addBanner(Context context, Map<String,String> headers, AddBannerRequest request){
        final MutableLiveData<AddBannerApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.addBanner(headers,request).enqueue(new Callback<AddBannerResponse>() {
            @Override
            public void onResponse(Call<AddBannerResponse> call, Response<AddBannerResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new AddBannerApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new AddBannerApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<AddBannerResponse> call, Throwable t) {
                responseLiveData.setValue(new AddBannerApiResponse(t));
            }
        });

        return   responseLiveData;
    }

    public MutableLiveData<BannerListApiResponse> getBannerList(Context context, Map<String,String> headers, String offset, String page, String id){
        final MutableLiveData<BannerListApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.getBannerList(headers,offset,page,id).enqueue(new Callback<BannerListResponse>() {
            @Override
            public void onResponse(Call<BannerListResponse> call, Response<BannerListResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new BannerListApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new BannerListApiResponse(response.body()));
                    }
                }
            }
            
            @Override
            public void onFailure(Call<BannerListResponse> call, Throwable t) {
                responseLiveData.setValue(new BannerListApiResponse(t));
            }
        });

        return   responseLiveData;
    }

    public MutableLiveData<BannerListApiResponse> publishDayCare(Context context, Map<String,String> headers, String id, String status){
        final MutableLiveData<BannerListApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.publishDayCare(headers,id,status).enqueue(new Callback<BannerListResponse>() {
            @Override
            public void onResponse(Call<BannerListResponse> call, Response<BannerListResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new BannerListApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new BannerListApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<BannerListResponse> call, Throwable t) {
                responseLiveData.setValue(new BannerListApiResponse(t));
            }
        });

        return   responseLiveData;
    }
}
