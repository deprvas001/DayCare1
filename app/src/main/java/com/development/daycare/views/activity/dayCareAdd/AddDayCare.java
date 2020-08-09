package com.development.daycare.views.activity.dayCareAdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.development.daycare.R;
import com.development.daycare.UserLocationMap;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityAddDayCareBinding;
import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.ImagePickerScreen;
import com.development.daycare.views.activity.dayCareBanner.DayCareBanner;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddDayCare extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
ActivityAddDayCareBinding careBinding;
AddCareRequest careRequest = new AddCareRequest();
ShowCareData careData;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        careBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_day_care);
        careBinding.setAddCare(careRequest);
        checkIntent();
        setClickListener();
    }

    private void setClickListener(){
        getUserLocation();
        careBinding.btnNext.setOnClickListener(this);
        careBinding.back.setOnClickListener(this);
        careBinding.radioCctv.setOnCheckedChangeListener(this);
        careBinding.radioType.setOnCheckedChangeListener(this);
        careBinding.uploadImage.setOnClickListener(this);
        careBinding.radioGroupTransport.setOnCheckedChangeListener(this);
        careBinding.inputMinAge.setOnClickListener(this);
        careBinding.inputMaxAge.setOnClickListener(this);
    }

    private void getUserLocation() {

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Day Care Location", location.toString());

                //Get All detail of location
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try{
                    List<Address> listAddress = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                    if(listAddress !=null && listAddress.size()>0){
                        Log.i("Place Info", listAddress.get(0).toString());
                        String address="";

                       address =  listAddress.get(0).getAddressLine(0);

                       careBinding.inputAddress.setText(address);

                        careRequest.setDaycare_latitude(String.valueOf(location.getLatitude()));
                        careRequest.setDaycare_longitude(String.valueOf(location.getLongitude()));
                       /* if(listAddress.get(0).getThoroughfare() !=null){
                            address += listAddress.get(0).getThoroughfare() + " ";
                        }

                        if(listAddress.get(0).getLocality() !=null){
                            address += listAddress.get(0).getLocality() + " ";
                        }

                        if(listAddress.get(0).getPostalCode() !=null){
                            address += listAddress.get(0).getPostalCode() + " ";
                        }

                        if(listAddress.get(0).getAdminArea() !=null){
                            address += listAddress.get(0).getAdminArea() + " ";
                        }*/
                      //  eature=1015,admin=Delhi,sub-admin=Shahdara,locality=Delhi,thoroughfare=null,postalCode=110093,countryCode=IN,countryName=India,hasLatitude=true,latitude=28.691813000000003,hasLongitude=true,longitude=77.29782999999999
                      //  Toast.makeText(AddDayCare.this, address, Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next:
                if(careBinding.inputName.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.care_name_empty), Toast.LENGTH_SHORT).show();
                    return;
                }else if(careBinding.inputAddress.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.address_empty), Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(careBinding.inputShortDescription.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.short_empty), Toast.LENGTH_SHORT).show();
                    return;
                }  else if(careBinding.inputDescription.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.description_empty), Toast.LENGTH_SHORT).show();
                    return;
                } else if(careBinding.inputMinAge.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.min_age_empty), Toast.LENGTH_SHORT).show();
                    return;
                }else if(careBinding.inputMaxAge.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.max_age_empty), Toast.LENGTH_SHORT).show();
                    return;
                }else if(careBinding.inputMinBudget.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.mini_fee_empty), Toast.LENGTH_SHORT).show();
                    return;
                }else if(careBinding.inputMaxBudget.getText().toString().isEmpty()){
                    Toast.makeText(this, getString(R.string.max_fee_empty), Toast.LENGTH_SHORT).show();
                    return;
                }

                else{

                    goStepSecond();
                }

                break;

            case R.id.back:
                finish();
                break;

            case R.id.input_min_age:
                agePicker("1");
                break;

            case R.id.input_max_age:
                agePicker("2");
                break;

            case R.id.upload_image:
                Intent intent = new Intent(AddDayCare.this, ImagePickerScreen.class);
                startActivityForResult(intent, 105);// Activity is started with requestCode 2
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int chekedId) {
        switch (chekedId){
            case R.id.yes:
                careRequest.setDaycare_cctv_value("1");
                break;

            case R.id.no:
                careRequest.setDaycare_cctv_value("0");
                break;

            case R.id.transport_yes:
                careRequest.setTransport_required("1");
                break;

            case R.id.transport_no:
                careRequest.setTransport_required("0");
                break;

            case R.id.international:
                careRequest.setDaycare_type("1");
                break;

            case R.id.local:
                careRequest.setDaycare_type("0");
                break;
        }
    }

    private void goStepSecond(){
        if(careRequest.getDaycare_logo_url() !=null){
          //  careRequest.setDaycare_logo_url("Url.png");
            Intent intent = new Intent(AddDayCare.this,AddDayCareSecond.class);
            intent.putExtra(ApiConstant.DAY_ONE,careRequest);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, getString(R.string.logo_empty), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 105) {
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
                                .into((careBinding.uploadImage));

                        careRequest.setDaycare_logo_url("Testing image url");

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
                        careBinding.uploadImage.setImageBitmap(bitmap);

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
        careRequest.setDaycare_logo_url("data:image/jpeg;base64,"+image_string);

    }

    private void checkIntent() {
        if (getIntent().getExtras()!= null) {

            if(getIntent().getExtras().containsKey(ApiConstant.DAYCARE_ID)){
                careData = getIntent().getExtras().getParcelable(ApiConstant.DAYCARE_ID);
                careRequest.setDaycare_id(careData.getDaycare_id());
                careRequest.setDaycare_name(careData.getDaycare_name());
                careRequest.setDaycare_address(careData.getDaycare_address());
                careRequest.setDaycare_short_description(careData.getDaycare_short_description());
                careRequest.setDaycare_long_description(careData.getDaycare_long_description());
                careRequest.setDaycare_child_age_min_value(careData.getDaycare_child_age_min_value());
                careRequest.setDaycare_child_age_max_value(careData.getDaycare_child_age_max_value());
                careRequest.setDaycare_budget_min_value(careData.getDaycare_budget_min_value());
                careRequest.setDaycare_budget_max_value(careData.getDaycare_budget_max_value());
                careRequest.setContact_info(careData.getDaycare_contact_info());
                careRequest.setMeal_menu_list(careData.getDaycare_menu_list());
                careRequest.setSubject_list(careData.getDaycare_tution_subject_list());
                careRequest.setOpening_time(careData.getDaycare_open_hours());
                careRequest.setDaycare_type_of_meals(careData.getDaycare_type_of_meals());

                    if(careData.getDaycare_cctv_value().equals("1")){
                        careRequest.setDaycare_cctv_value("1");
                        careBinding.yes.setChecked(true);

                    }else{
                        careRequest.setDaycare_cctv_value("0");
                        careBinding.no.setChecked(true);
                    }

                    if(careData.getDaycare_transportation_required().equals("1")){
                        careRequest.setTransport_required("1");
                        careBinding.transportYes.setChecked(true);
                    }else{
                        careRequest.setTransport_required("0");
                        careBinding.transportNo.setChecked(true);
                    }

                    if(careData.getDaycare_type().equals("1")){
                        careRequest.setDaycare_type("1");
                        careBinding.international.setChecked(true);
                    }else{
                        careRequest.setDaycare_type("0");
                        careBinding.local.setChecked(true);
                    }

                   careRequest.setDaycare_logo_url(careData.getDaycare_logo_url());

                   Picasso.get()
                        .load(careData.getDaycare_logo_url())
                        // .placeholder(R.drawable.image1)
                        //   .error(R.drawable.err)
                        .into(careBinding.uploadImage);

            }
        }else{
            careRequest.setDaycare_cctv_value("1");
            careRequest.setTransport_required("1");
            careRequest.setDaycare_type("0");
        }

    }

    private void agePicker(String type){
        final NumberPicker numberPicker = new NumberPicker(AddDayCare.this);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(2);


        AlertDialog.Builder builder = new AlertDialog.Builder(AddDayCare.this);
        if(type.equals("1")){
            builder.setTitle("Child Minimum Age");
        }else{
            builder.setTitle("Child Maximum Age");
        }


        builder.setView(numberPicker);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(type.equals("1")){
                    careBinding.inputMinAge.setText(String.valueOf(numberPicker.getValue()));
                }else{
                    careBinding.inputMaxAge.setText(String.valueOf(numberPicker.getValue()));
                }
               // dialogHost.onPositiveButton(numberPicker.getValue());
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialogHost.onCancelButton();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                }
            }
        }
    }

}
