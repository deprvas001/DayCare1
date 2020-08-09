package com.development.daycare.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.adapter.HomeSlideAdapter;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityDayCareDetailBinding;
import com.development.daycare.model.addBanner.BannerListApiResponse;
import com.development.daycare.model.addBanner.BannerResponseListData;
import com.development.daycare.model.homeModel.HomeSlider;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.session.SessionManager;
import com.development.daycare.util.Util;
import com.development.daycare.views.activity.dayCareAdd.AddDayCare;
import com.development.daycare.views.activity.dayCareBanner.AddBannerViewModel;
import com.development.daycare.views.activity.dayCareBanner.DayCareBanner;
import com.development.daycare.views.activity.dayCareBanner.ShowBannerList;
import com.development.daycare.views.activity.daycareActivities.CareActivityList;
import com.development.daycare.views.activity.showDayCare.ShowDayCare;
import com.github.chrisbanes.photoview.PhotoView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayCareDetail extends BaseActivity implements View.OnClickListener {
    ActivityDayCareDetailBinding detailBinding;
    ShowCareData careData;
    AddBannerViewModel viewModel;
    List<HomeSlider> sliderList = new ArrayList<>();
    SessionManager session;
    String token;
    Dialog dialog;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailBinding = DataBindingUtil.setContentView(this,R.layout.activity_day_care_detail);
        checkIntent();
        setClickListener();
    }

    private void setClickListener(){
        detailBinding.fab.setOnClickListener(this);
        detailBinding.btnGallery.setOnClickListener(this);
        detailBinding.btnActivity.setOnClickListener(this);
        detailBinding.editDetail.setOnClickListener(this);
        detailBinding.btnPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                String geoUri = "http://maps.google.com/maps?q=loc:" + careData.getDaycare_latitude() + "," + careData.getDaycare_longitude() + " (" + "Day Care" + ")";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(geoUri));
                startActivity(intent);
                break;

            case R.id.btn_gallery:
                intent =new  Intent(DayCareDetail.this, ShowBannerList.class);
                intent.putExtra(ApiConstant.DAYCARE_ID,careData.getDaycare_id());
                startActivity(intent);
                break;

            case R.id.btn_activity:
                intent =new  Intent(DayCareDetail.this, CareActivityList.class);
                intent.putExtra(ApiConstant.DAYCARE_ID,careData.getDaycare_id());
                startActivity(intent);
                break;

            case R.id.edit_detail:
                intent =new  Intent(DayCareDetail.this, AddDayCare.class);
                intent.putExtra(ApiConstant.DAYCARE_ID,careData);
                startActivity(intent);
                break;

            case R.id.btn_publish:
                 pubishDayCare();
                break;
        }
    }


    private void checkIntent() {
        if (getIntent() != null) {

            if(getIntent().getExtras().containsKey(ApiConstant.DAYCARE_ID)){
                careData = getIntent().getExtras().getParcelable(ApiConstant.DAYCARE_ID);

                detailBinding.overviewDesc.setText(careData.getDaycare_short_description()+"\n"+
                        careData.getDaycare_long_description());
                detailBinding.dayCareName.setText(careData.getDaycare_name());
                detailBinding.dayCareLocation.setText("Location "+careData.getDaycare_address());
                detailBinding.maxAge.setText(careData.getDaycare_child_age_min_value()+"year - "+
                        careData.getDaycare_child_age_max_value()+"year");

                detailBinding.maxFees.setText(careData.getDaycare_budget_min_value()+ " - "+
                        careData.getDaycare_budget_max_value());

                detailBinding.timingValue.setText(careData.getDaycare_open_hours().get(0).getOpen_time()+
                        " "+careData.getDaycare_open_hours().get(0).getClose_time());

                if(careData.getDaycare_cctv_value() .equals("0")){
                    detailBinding.cctvValue.setText("No");
                }else {
                    detailBinding.cctvValue.setText("Yes");
                }

                if(careData.getDaycare_transportation_required().equals("0")){
                    detailBinding.transportValue.setText("No");
                }else{
                    detailBinding.transportValue.setText("Yes");
                }

                if(careData.getDaycare_type().equals("0")){
                    detailBinding.typeValue.setText("Local");
                }else{
                    detailBinding.typeValue.setText("International");
                }

                detailBinding.labelValue.setText(careData.getDaycare_contact_info().get(0).getContact_label());
                detailBinding.contactName.setText(careData.getDaycare_contact_info().get(0).getContact_name());
                detailBinding.emailValue.setText(careData.getDaycare_contact_info().get(0).getContact_email());
                detailBinding.contactValue.setText(careData.getDaycare_contact_info().get(0).getContact_phone());

                if(careData.getDaycare_status().equals("0")){
                    detailBinding.btnPublish.setVisibility(View.VISIBLE);
                }else if(careData.getDaycare_status().equals("1")){
                    detailBinding.btnPublish.setVisibility(View.GONE);
                }

                getSession();
            }
            }else{
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void setSliderAndView(List<HomeSlider> sliderList) {
        final HomeSlideAdapter adapter = new HomeSlideAdapter(DayCareDetail.this, sliderList);
        adapter.setCount(sliderList.size());

        detailBinding.imageSlider.setSliderAdapter(adapter);

        detailBinding.imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        detailBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        detailBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        detailBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        detailBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        detailBinding.imageSlider.startAutoCycle();

        detailBinding.imageSlider.setOnIndicatorClickListener(position ->
                detailBinding.imageSlider.setCurrentPagePosition(position));

    }


    private void getBannerList() {
        showProgressDialog(getString(R.string.loading));
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, token);


        viewModel = ViewModelProviders.of(this).get(AddBannerViewModel.class);
        viewModel.getBannerList(this, headers, "0", "10",careData.getDaycare_id()).observe(this, new Observer<BannerListApiResponse>() {
            @Override
            public void onChanged(BannerListApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    if (apiResponse.getResponse().getStatus() == 1) {
                        if(apiResponse.getResponse().getData().size()>0){
                        List<BannerResponseListData> bannerList = apiResponse.getResponse().getData();
                            for(int i=0;i<bannerList.size();i++){
                                HomeSlider slider = new HomeSlider();
                                slider.setImage(bannerList.get(i).getDaycare_banner_url());
                                slider.setTitle(bannerList.get(i).getDaycare_banner_label());
                                sliderList.add(slider);
                            }
                            setSliderAndView(sliderList);
                         }
                    }else{
                        // Toast.makeText(ShowBannerList.this, apiResponse.getResponse()., Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // call failed.
                    Toast.makeText(DayCareDetail.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getSession() {
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

        getBannerList();

    }


    private void pubishDayCare() {
        showProgressDialog(getString(R.string.loading));
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, token);


        viewModel = ViewModelProviders.of(this).get(AddBannerViewModel.class);
        viewModel.publishDayCare(this, headers, careData.getDaycare_id(),"1").observe(this, new Observer<BannerListApiResponse>() {
            @Override
            public void onChanged(BannerListApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    if (apiResponse.getStatus() == 1) {
                         Toast.makeText(DayCareDetail.this,"Successfully Publish", Toast.LENGTH_SHORT).show();
                         detailBinding.btnPublish.setVisibility(View.GONE);
                     }else{
                         Toast.makeText(DayCareDetail.this, "Try Later.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // call failed.
                    Toast.makeText(DayCareDetail.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}
