package com.development.daycare.views.activity.partnerProfile;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityPartnerProfileBinding;
import com.development.daycare.model.partnerprofile.ProfileApiResponse;
import com.development.daycare.model.partnerprofile.ProfileData;
import com.development.daycare.session.SessionManager;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.ImagePickerScreen;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartnerProfile extends BaseActivity implements View.OnClickListener {
ActivityPartnerProfileBinding partnerProfileBinding;
    PartnerProfileViewModel viewModel;
    SessionManager session;
    String token;
    ProfileData profileData = new ProfileData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        partnerProfileBinding = DataBindingUtil.setContentView(this,R.layout.activity_partner_profile);
        setClickListener();
        getSession();
    }


    private void getProfile(String token, String name, String email, String phone) {
        partnerProfileBinding.progressBar.setVisibility(View.VISIBLE);

        Map<String,String> headers = headers(token);

        viewModel = ViewModelProviders.of(this).get(PartnerProfileViewModel.class);

        viewModel.getProfile(this, headers).observe(this, new Observer<ProfileApiResponse>() {
            @Override
            public void onChanged(ProfileApiResponse apiResponse) {
                partnerProfileBinding.progressBar.setVisibility(View.GONE);
                partnerProfileBinding.layout.setVisibility(View.VISIBLE);

                if(apiResponse.getResponse()!=null){
                    if(apiResponse.getResponse().getStatus() ==1){
                        partnerProfileBinding.inputName.setText(apiResponse.getResponse().getData().get(0).getUser_first_name());
                        partnerProfileBinding.inputContact.setText(apiResponse.getResponse().getData().get(0).getUser_phone_number());
                        partnerProfileBinding.inputEmail.setText(apiResponse.getResponse().getData().get(0).getUser_email());
                    }

                }else{
                    Toast.makeText(PartnerProfile.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
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

        getProfile(token,name,email,phone);

    }

    private void updateProfile(){

        profileData.setUser_email(partnerProfileBinding.inputEmail.getText().toString());
        profileData.setUser_first_name(partnerProfileBinding.inputName.getText().toString());
        profileData.setUser_last_name((partnerProfileBinding.inputName.getText().toString()));
        profileData.setUser_phone_number(partnerProfileBinding.inputContact.getText().toString());

        partnerProfileBinding.progressBar.setVisibility(View.VISIBLE);

        Map<String,String> headers = headers(token);

        viewModel = ViewModelProviders.of(this).get(PartnerProfileViewModel.class);

        viewModel.updateProfile(this, headers,profileData).observe(this, new Observer<ProfileApiResponse>() {
            @Override
            public void onChanged(ProfileApiResponse apiResponse) {
                partnerProfileBinding.progressBar.setVisibility(View.GONE);
                partnerProfileBinding.layout.setVisibility(View.VISIBLE);

                if(apiResponse.getResponse()!=null){
                    if(apiResponse.getResponse().getStatus() ==1){
                        partnerProfileBinding.inputName.setText(apiResponse.getResponse().getData().get(0).getUser_first_name());
                        partnerProfileBinding.inputContact.setText(apiResponse.getResponse().getData().get(0).getUser_phone_number());
                        partnerProfileBinding.inputEmail.setText(apiResponse.getResponse().getData().get(0).getUser_email());
                    }

                }else{
                    Toast.makeText(PartnerProfile.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private Map<String,String> headers(String token){
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, token);

        return  headers;

    }

    private void setClickListener(){
        partnerProfileBinding.back.setOnClickListener(this);
        partnerProfileBinding.btnUpdate.setOnClickListener(this);
        partnerProfileBinding.profileImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

            case R.id.btn_update:
                if(partnerProfileBinding.inputEmail.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.email_empty), Toast.LENGTH_SHORT).show();
                    break;
                }else if(partnerProfileBinding.inputName.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.name_empty), Toast.LENGTH_SHORT).show();
                    break;
                }else if(partnerProfileBinding.inputContact.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.contact_phone_empty), Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    updateProfile();
                }

                break;

            case R.id.profile_image:
                Intent intent = new Intent(PartnerProfile.this, ImagePickerScreen.class);
                startActivityForResult(intent, 103);// Activity is started with requestCode 2
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 103) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                ArrayList<Image> images = data.getParcelableArrayListExtra(ApiConstant.IMAGE_PICK);

                if (images.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(images.get(0).getId()));
                        RequestOptions options = new RequestOptions().placeholder(R.drawable.noimage)
                                .error(R.drawable.noimage);

                        Glide.with(this)
                                .load(uri)
                                .apply(options)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(partnerProfileBinding.profileImage);

                         profileData.setUser_profile_image("thttps://img.etimg.com/thumb/msid-67725931,width-300,imgsize-137759,resizemode-4/uk-expresses-regret-at-burning-of-indian-flag-by-separatist-groups.jpg");

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            convetBitmapString(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {

                        // do your logic here...
                        BitmapFactory.Options options = new BitmapFactory.Options();

                        // downsizing image as it throws OutOfMemory Exception for larger
                        // images
                        //  options.inSampleSize = calculteInSampleSize(option,500,500)
                        options.inSampleSize = 2;

                        String path = images.get(0).getPath();
                        //   Toast.makeText(BookingDetails.this, path, Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                        partnerProfileBinding.profileImage.setImageBitmap(bitmap);

                        convetBitmapString(bitmap);

                    }
                }
            }

        }
    }

    private void convetBitmapString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String  image_string = Base64.encodeToString(byteArray, Base64.DEFAULT);
        profileData.setUser_profile_image(image_string);

    }

}
