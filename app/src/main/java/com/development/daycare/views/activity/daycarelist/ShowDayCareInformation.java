package com.development.daycare.views.activity.daycarelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.adapter.DayCareBannerAdapter;
import com.development.daycare.adapter.HomeSlideAdapter;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityShowDayCareInformationBinding;
import com.development.daycare.model.addDay.DayCareBannerList;
import com.development.daycare.model.homeModel.HomeSlider;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.session.SessionManager;
import com.development.daycare.views.activity.DayCareDetail;
import com.development.daycare.views.activity.dayCareBanner.AddBannerViewModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ShowDayCareInformation extends AppCompatActivity implements View.OnClickListener {
    ActivityShowDayCareInformationBinding informationBinding;
    ShowCareData careData;
    List<HomeSlider> sliderList = new ArrayList<>();

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        informationBinding = DataBindingUtil.setContentView(this,R.layout.activity_show_day_care_information);
        checkIntent();
    }


        private void checkIntent() {
            if (getIntent() != null) {

                if(getIntent().getExtras().containsKey(ApiConstant.DAYCARE_ID)){
                    careData = getIntent().getExtras().getParcelable(ApiConstant.DAYCARE_ID);

                    informationBinding.overviewDesc.setText(careData.getDaycare_short_description()+"\n"+
                            careData.getDaycare_long_description());
                    informationBinding.dayCareName.setText(careData.getDaycare_name());
                    informationBinding.dayCareLocation.setText("Location "+careData.getDaycare_address());
                    informationBinding.maxAge.setText(careData.getDaycare_child_age_min_value()+"year - "+
                            careData.getDaycare_child_age_max_value()+"year");

                    informationBinding.maxFees.setText(careData.getDaycare_budget_min_value()+ " - "+
                            careData.getDaycare_budget_max_value());

                    informationBinding.timingValue.setText(careData.getDaycare_open_hours().get(0).getOpen_time()+
                            " "+careData.getDaycare_open_hours().get(0).getClose_time());

                    if(careData.getDaycare_cctv_value() .equals("0")){
                        informationBinding.cctvValue.setText("No");
                    }else {
                        informationBinding.cctvValue.setText("Yes");
                    }

                    if(careData.getDaycare_transportation_required().equals("0")){
                        informationBinding.transportValue.setText("No");
                    }else{
                        informationBinding.transportValue.setText("Yes");
                    }

                    if(careData.getDaycare_type().equals("0")){
                        informationBinding.typeValue.setText("Local");
                    }else{
                        informationBinding.typeValue.setText("International");
                    }

                    informationBinding.labelValue.setText(careData.getDaycare_contact_info().get(0).getContact_label());
                    informationBinding.contactName.setText(careData.getDaycare_contact_info().get(0).getContact_name());
                    informationBinding.emailValue.setText(careData.getDaycare_contact_info().get(0).getContact_email());
                    informationBinding.contactValue.setText(careData.getDaycare_contact_info().get(0).getContact_phone());

                    setSliderAndView(careData.getDaycare_banner_list());

                    informationBinding.btnActivity.setOnClickListener(this);

                }
            }else{
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                finish();
            }

        }


    private void setSliderAndView(List<DayCareBannerList> sliderList) {
        final DayCareBannerAdapter adapter = new DayCareBannerAdapter(this, sliderList);
        adapter.setCount(sliderList.size());

        informationBinding.imageSlider.setSliderAdapter(adapter);

        informationBinding.imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        informationBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        informationBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        informationBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        informationBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        informationBinding.imageSlider.startAutoCycle();

        informationBinding.imageSlider.setOnIndicatorClickListener(position ->
                informationBinding.imageSlider.setCurrentPagePosition(position));

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_activity:
                  Intent intent = new Intent(this,ShowCareActivity.class);
                  intent.putParcelableArrayListExtra(ApiConstant.DAY_CARE_ACTIVITY,(ArrayList)careData.getDaycare_inhouse_activity_list());
                  startActivity(intent);
                break;
        }
    }
}
