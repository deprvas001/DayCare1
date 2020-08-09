package com.development.daycare.views.activity.changePassword;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.model.changePassword.ChangePasswordRequest;
import com.development.daycare.model.changePassword.PasswordApiResponse;
import com.development.daycare.model.changePassword.PasswordResponse;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordRepository {

    private static ChangePasswordRepository passwordRepository = null;
    private ShipmentApi shipmentApi;

    public ChangePasswordRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static ChangePasswordRepository getInstance(){
        if(passwordRepository == null)
            passwordRepository =new ChangePasswordRepository();
        return passwordRepository;
    }

    public MutableLiveData<PasswordApiResponse> changePassword(Context context, Map<String,String> headers, ChangePasswordRequest request){
        final MutableLiveData<PasswordApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.changePassword(headers,request).enqueue(new Callback<PasswordResponse>() {
            @Override
            public void onResponse(Call<PasswordResponse> call, Response<PasswordResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new PasswordApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new PasswordApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<PasswordResponse> call, Throwable t) {
                responseLiveData.setValue(new PasswordApiResponse(t));
            }
        });

        return   responseLiveData;
    }
}
