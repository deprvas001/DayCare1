package com.development.daycare.views.activity.changePassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityChangePasswordBinding;
import com.development.daycare.model.changePassword.ChangePasswordRequest;
import com.development.daycare.model.changePassword.PasswordApiResponse;
import com.development.daycare.session.SessionManager;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.dayCareAdd.AddCareViewModel;
import com.development.daycare.views.activity.dayCareAdd.AddDayCareSecond;
import com.development.daycare.views.activity.showDayCare.ShowDayCare;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends BaseActivity implements View.OnClickListener {
ActivityChangePasswordBinding passwordBinding;
SessionManager session;
String token;
PasswordViewModel viewModel;
ChangePasswordRequest passwordRequest = new ChangePasswordRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passwordBinding = DataBindingUtil.setContentView(this,R.layout.activity_change_password);
        passwordBinding.setPassword(passwordRequest);
        getSession();
        setClickListener();
    }
    private void setClickListener(){
        passwordBinding.back.setOnClickListener(this);
        passwordBinding.btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

            case R.id.btn_update:
                 if(passwordRequest.getOld_password()==null){
                     Toast.makeText(this, getString(R.string.old_password_empty), Toast.LENGTH_SHORT).show();
                     break;
                 }else if(passwordRequest.getNew_password() == null){
                     Toast.makeText(this, getString(R.string.new_password_empty), Toast.LENGTH_SHORT).show();
                     break;
                 }else{
                     if(passwordRequest.getNew_password().equals(passwordBinding.inputReconfirm.getText().toString())){
                         changePassword();
                     }else{
                         Toast.makeText(this, getString(R.string.not_same), Toast.LENGTH_SHORT).show();
                     }
                 }
                break;
        }
    }

    private void getSession(){
        session = new SessionManager(this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        token = user.get(SessionManager.KEY_TOKEN);
        String phone = user.get(SessionManager.KEY_PHONE);

        passwordRequest.setUser_name(email);
    }

    private void changePassword() {
        showProgressDialog(getString(R.string.loading));

        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, token);


        viewModel = ViewModelProviders.of(this).get(PasswordViewModel.class);
        viewModel.changePassword(this, headers, passwordRequest).observe(this, new Observer<PasswordApiResponse>() {
            @Override
            public void onChanged(PasswordApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    Toast.makeText(ChangePassword.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                   if(apiResponse.getResponse().getStatus() ==1){
                      finish();
                   }
                } else {
                    // call failed.
                    Toast.makeText(ChangePassword.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
