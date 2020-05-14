package com.development.daycare.views.fragment.home;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.homeModel.HomeApiResponse;
import com.development.daycare.model.homeModel.HomeResponse;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    private static HomeRepository homeRepository = null;
    private ShipmentApi shipmentApi;

    public HomeRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static HomeRepository getInstance(){
        if(homeRepository == null)
            homeRepository =new HomeRepository();
        return homeRepository;
    }

    public MutableLiveData<HomeApiResponse> getData(Context context, String type){
        final MutableLiveData<HomeApiResponse> homeResponseLiveData =new MutableLiveData<>();

        shipmentApi.getData(type).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if(response.code() == 401){
                    homeResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    homeResponseLiveData.setValue(new HomeApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        homeResponseLiveData.setValue(new HomeApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                homeResponseLiveData.setValue(new HomeApiResponse(t));
            }
        });

        return   homeResponseLiveData;
    }
}
