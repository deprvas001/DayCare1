package com.development.daycare.views.activity.showDayCare;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.model.showCareModel.ShowCareApiResponse;
import com.development.daycare.model.showCareModel.ShowCareResponse;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowCareRepository {
    private static ShowCareRepository showRepository = null;
    private ShipmentApi shipmentApi;

    public ShowCareRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static ShowCareRepository getInstance(){
        if(showRepository == null)
            showRepository =new ShowCareRepository();
        return showRepository;
    }

    public MutableLiveData<ShowCareApiResponse> getCareList(Context context, Map<String,String> headers,String offset,String per_page){
        final MutableLiveData<ShowCareApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.getCareList(headers,offset,per_page).enqueue(new Callback<ShowCareResponse>() {
            @Override
            public void onResponse(Call<ShowCareResponse> call, Response<ShowCareResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new ShowCareApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new ShowCareApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ShowCareResponse> call, Throwable t) {
                responseLiveData.setValue(new ShowCareApiResponse(t));
            }
        });

        return   responseLiveData;
    }
}
