package com.development.daycare.views.activity.forgotPassword;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;


import com.development.daycare.model.forgotModel.ForgotRequestModel;
import com.development.daycare.model.loginModel.LoginApiResponse;
import com.development.daycare.model.loginModel.LoginResponse;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotRepository {

    private static ForgotRepository forgotRepository = null;
    private ShipmentApi shipmentApi;

    public ForgotRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static ForgotRepository getInstance(){
        if(forgotRepository == null)
            forgotRepository =new ForgotRepository();
        return forgotRepository;
    }

    public MutableLiveData<LoginApiResponse> forgotPassword(Context context, ForgotRequestModel requestModel){
        final MutableLiveData<LoginApiResponse> loginResponseLiveData =new MutableLiveData<>();

        shipmentApi.forgotPassword(requestModel).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new LoginApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new LoginApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new LoginApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
