package com.development.daycare.views.activity.login;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.loginModel.LoginApiResponse;
import com.development.daycare.model.loginModel.LoginRequest;
import com.development.daycare.model.loginModel.LoginResponse;
import com.development.daycare.model.signupModel.SignUpResponse;
import com.development.daycare.model.signupModel.UserSignupRequest;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;
import com.development.daycare.views.activity.signup.SigupRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static LoginRepository signRepository = null;
    private ShipmentApi shipmentApi;

    public LoginRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static LoginRepository getInstance(){
        if(signRepository == null)
            signRepository =new LoginRepository();
        return signRepository;
    }

    public MutableLiveData<LoginApiResponse> login(Context context, Map<String,String> headers, LoginRequest request){
        final MutableLiveData<LoginApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.login(headers,request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new LoginApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new LoginApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                responseLiveData.setValue(new LoginApiResponse(t));
            }
        });

        return   responseLiveData;
    }
}
