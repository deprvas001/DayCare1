package com.development.daycare.views.activity.daycareActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityDayCareBinding;
import com.development.daycare.model.addBanner.AddBannerApiResponse;
import com.development.daycare.model.addCareActivity.AddActivityApiResponse;
import com.development.daycare.model.addCareActivity.AddActivityRequest;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.ImagePickerScreen;
import com.development.daycare.views.activity.PartnerCareHome;
import com.development.daycare.views.activity.dayCareBanner.AddBannerViewModel;
import com.development.daycare.views.activity.dayCareBanner.DayCareBanner;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DayCareActivity extends BaseActivity implements View.OnClickListener {
    ActivityDayCareBinding dayCareBinding;
    AddActivityRequest addActivityRequest = new AddActivityRequest();
    CareViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayCareBinding = DataBindingUtil.setContentView(this, R.layout.activity_day_care);
        checkIntent();
    }

    private void checkIntent() {
        if (getIntent() != null) {
            String day_care_id = getIntent().getExtras().getString(ApiConstant.DAYCARE_ID);
            addActivityRequest.setDaycare_id(day_care_id);

            dayCareBinding.setAddActivity(addActivityRequest);
        }

        setClickListener();
    }

    private void setClickListener() {

        dayCareBinding.btnNext.setOnClickListener(this);
        dayCareBinding.uploadImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                if (dayCareBinding.inputName.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.name_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (dayCareBinding.description.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.description_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (addActivityRequest.getDaycare_activity_image() ==null ||addActivityRequest.getDaycare_activity_image().isEmpty()) {
                    Toast.makeText(this, getString(R.string.image_empty), Toast.LENGTH_SHORT).show();
                    break;
                }else{
                   addCareActivity();
                }
                break;

            case R.id.upload_image:
                Intent intent = new Intent(DayCareActivity.this, ImagePickerScreen.class);
                startActivityForResult(intent, 102);// A
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 102) {
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
                                .into(dayCareBinding.uploadImage);
                        addActivityRequest.setDaycare_activity_image("Testing image url");

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
                        dayCareBinding.uploadImage.setImageBitmap(bitmap);

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
        addActivityRequest.setDaycare_activity_image(image_string);
    }

    private void addCareActivity(){
        showProgressDialog(getString(R.string.loading));

        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTg5MTI2MzIzLCJuYmYiOjE1ODkxMjYzMjMsImV4cCI6MTU5MDMzNTkyMywiZGF0YSI6eyJ1c2VyX3R5cGUiOiJEQVlDQVJFIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiIxMjFAZ21haWwuY29tIiwidXNlcl9pZCI6Ijg5IiwidXNlcl9sb2dfaWQiOjQzfX0.oZHnTt1fC75yB1V_Q7XoWpPKmXVBMIRtfrJJ9bpwVtA");


        viewModel = ViewModelProviders.of(this).get(CareViewModel.class);
        viewModel.addActivity(this, headers, addActivityRequest).observe(this, new Observer<AddActivityApiResponse>() {
            @Override
            public void onChanged(AddActivityApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    Toast.makeText(DayCareActivity.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    /*if (apiResponse.getResponse().getStatus() ==1) {

                    }*/
                    showCustomDialog();
                } else {
                    // call failed.
                    Toast.makeText(DayCareActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showCustomDialog(){
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.booking_success_dialog,null);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Button ok =(Button)dialogView.findViewById(R.id.buttonOk);
        Button cancel = (Button)dialogView.findViewById(R.id.button_cancel);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DayCareActivity.this, DayCareActivity.class);
                startActivity(i);
                alertDialog.dismiss();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                showHomeScreen();
            }
        });


        //finally creating the alert dialog and displaying it

    }

    private void showHomeScreen(){
        Intent i = new Intent(DayCareActivity.this, PartnerCareHome.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
