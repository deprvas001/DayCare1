package com.development.daycare.views.activity.signup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivitySignupScreenBinding;
import com.development.daycare.model.homeModel.HomeApiResponse;
import com.development.daycare.model.homeModel.HomeSlider;
import com.development.daycare.model.homeModel.MenuList;
import com.development.daycare.model.signupModel.SignUpApiResponse;
import com.development.daycare.model.signupModel.UserSignupRequest;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.HomeScreen;
import com.development.daycare.views.fragment.home.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignupScreen extends BaseActivity implements View.OnClickListener {
    ActivitySignupScreenBinding screenBinding;
    View contextView;
    SignupViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup_screen);
        setClickListener();
    }

    private void setClickListener() {
        screenBinding.btnNext.setOnClickListener(this);
        screenBinding.signIn.setOnClickListener(this);
        screenBinding.back.setOnClickListener(this);
        contextView = findViewById(android.R.id.content);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_next) {
            if (screenBinding.inputName.getText().toString().isEmpty()) {
                Snackbar.make(contextView, getString(R.string.name_empty), Snackbar.LENGTH_LONG).show();
                return;
            } else if (screenBinding.inputEmail.getText().toString().isEmpty()) {
                Snackbar.make(contextView, getString(R.string.email_empty), Snackbar.LENGTH_LONG).show();
                return;
            } else if (screenBinding.inputPhone.getText().toString().isEmpty()) {
                Snackbar.make(contextView, getString(R.string.phone_empty), Snackbar.LENGTH_LONG).show();
                return;
            } else if (screenBinding.password.getText().toString().isEmpty()) {
                Snackbar.make(contextView, getString(R.string.password_empty), Snackbar.LENGTH_LONG).show();
                return;
            } else if (screenBinding.policyCheck.isChecked()) {

                if (android.util.Patterns.EMAIL_ADDRESS.matcher(screenBinding.inputEmail.getText().toString()).matches()) {
                    signUp();
                } else {
                    Snackbar.make(contextView, getString(R.string.not_valid_email), Snackbar.LENGTH_LONG).show();
                    return;
                }

            } else {
                Snackbar.make(contextView, getString(R.string.accept_terms), Snackbar.LENGTH_LONG).show();
                return;
            }
        }else if(view.getId() == R.id.sign_in){
               finish();
        }else if(view.getId() == R.id.back){
            finish();
        }
    }


    private void signUp(){
        showProgressDialog(getResources().getString(R.string.loading));
        UserSignupRequest request =new UserSignupRequest();
        request.setUser_email(screenBinding.inputEmail.getText().toString());
        request.setUser_first_name(screenBinding.inputName.getText().toString());
        request.setUser_last_name(screenBinding.inputName.getText().toString());
        request.setUser_phone_number(screenBinding.inputPhone.getText().toString());
        request.setUser_password(screenBinding.password.getText().toString());

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);


        viewModel = ViewModelProviders.of(this).get(SignupViewModel.class);

        viewModel.setSignUp(this,headers, request).observe(this, apiResponse -> {
            hideProgressDialog();

            if (apiResponse.response != null) {
                Toast.makeText(this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
