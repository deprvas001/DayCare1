package com.development.daycare.views.activity.signup;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.signupModel.SignUpApiResponse;
import com.development.daycare.model.signupModel.SignUpResponse;
import com.development.daycare.model.signupModel.UserSignupRequest;
import com.development.daycare.networking.RetrofitService;
import com.development.daycare.networking.ShipmentApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigupRepository {

    private static SigupRepository signRepository = null;
    private ShipmentApi shipmentApi;

    public SigupRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static SigupRepository getInstance(){
        if(signRepository == null)
            signRepository =new SigupRepository();
        return signRepository;
    }

    public MutableLiveData<SignUpApiResponse> setSignUp(Context context, Map<String,String> headers, UserSignupRequest signPostRequest){
        final MutableLiveData<SignUpApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.userSignUp(headers,signPostRequest).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new SignUpApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new SignUpApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                responseLiveData.setValue(new SignUpApiResponse(t));
            }
        });

        return   responseLiveData;
    }
}
