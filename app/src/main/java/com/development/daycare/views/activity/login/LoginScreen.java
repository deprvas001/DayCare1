package com.development.daycare.views.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityLoginScreenBinding;
import com.development.daycare.model.loginModel.LoginApiResponse;
import com.development.daycare.model.loginModel.LoginRequest;
import com.development.daycare.session.SessionManager;
import com.development.daycare.views.ProfileScreen;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.PartnerCareHome;
import com.development.daycare.views.activity.PartnerHome;
import com.development.daycare.views.activity.forgotPassword.ForgotPassword;
import com.development.daycare.views.activity.signup.SignupScreen;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends BaseActivity implements View.OnClickListener {
ActivityLoginScreenBinding loginScreenBinding;
    private boolean isVisible= false;
    LoginViewModel viewModel;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginScreenBinding = DataBindingUtil.setContentView(this,R.layout.activity_login_screen);
        initializeView();
        setClickListener();
    }

    private void setClickListener(){
        loginScreenBinding.signUp.setOnClickListener(this);
        loginScreenBinding.btnLogin.setOnClickListener(this);
        loginScreenBinding.passwordVisibility.setOnClickListener(this);
        loginScreenBinding.forgotPassword.setOnClickListener(this);
    }

    private void initializeView(){
        session = new SessionManager(getApplicationContext());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_up:
                startActivity(new Intent(this, SignupScreen.class));
                break;

            case R.id.forgot_password:
                startActivity(new Intent(this, ForgotPassword.class));
                break;

            case R.id.btn_login:

                if(loginScreenBinding.inputEmail.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }else if(loginScreenBinding.inputPassword.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(loginScreenBinding.inputEmail.getText().toString()).matches()) {
                          userLogin();
                    }
                    else {
                        loginScreenBinding.inputEmail.setError("Invalid Email Address");
                    }
                }
                // startActivity(new Intent(this, ShowHomeScreen.class));

                break;

            case R.id.password_visibility:
                if(!isVisible){
                    isVisible = true;
                    loginScreenBinding.passwordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_black_24dp));
                    loginScreenBinding.inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    isVisible = false;
                    loginScreenBinding.passwordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_off_black_24dp));
                    loginScreenBinding.inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }


    private void userLogin() {
        showProgressDialog(getString(R.string.loading));

        LoginRequest requestModel = new LoginRequest();
        requestModel.setUser_name(loginScreenBinding.inputEmail.getText().toString());
        requestModel.setUser_password(loginScreenBinding.inputPassword.getText().toString());

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);


        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        viewModel.login(this, headers,requestModel).observe(this, new Observer<LoginApiResponse>() {
            @Override
            public void onChanged(LoginApiResponse loginApiResponse) {
                hideProgressDialog();
                if (loginApiResponse.getStatus_code() == 400 || loginApiResponse.getStatus_code() == 401
                        || loginApiResponse.getStatus_code() == 500) {
                    // handle error here
                    Toast.makeText(LoginScreen.this, loginApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (loginApiResponse.response != null) {
                    Toast.makeText(LoginScreen.this, loginApiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                    if (loginApiResponse.getResponse().getStatus() == 1) {
                        showProfileScreen(loginApiResponse);
                    }
                } else {
                    // call failed.
                    Toast.makeText(LoginScreen.this, loginApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showProfileScreen(LoginApiResponse loginApiResponse){

        String token = loginApiResponse.getResponse().getData().getAuthenticateToken();
        String userID = loginApiResponse.getResponse().getData().getUser_id();
        String userType = loginApiResponse.getResponse().getData().getUser_type();
        String name = loginApiResponse.getResponse().getData().getUser_name();
        String phone = loginApiResponse.getResponse().getData().getUser_phone();
        String email = loginApiResponse.getResponse().getData().getUser_email();

        session.createLoginSession(name,
                email, userID, userType, phone,token);
        //  LoginActivity.this.showAlertDialog(LoginActivity.this, LoginActivity.this.getString(R.string.success));

        Intent i = new Intent(LoginScreen.this, PartnerCareHome.class);
        // Closing all the Activities
                      /*  i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        // Staring Login Activity*/
        startActivity(i);
    }
}
