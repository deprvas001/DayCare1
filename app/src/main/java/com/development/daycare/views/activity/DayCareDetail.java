package com.development.daycare.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityDayCareDetailBinding;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.views.activity.dayCareBanner.DayCareBanner;
import com.development.daycare.views.activity.dayCareBanner.ShowBannerList;

public class DayCareDetail extends AppCompatActivity implements View.OnClickListener {
ActivityDayCareDetailBinding detailBinding;
    ShowCareData careData;
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                String geoUri = "http://maps.google.com/maps?q=loc:" + "3.1390" + "," + "101.6869" + " (" + "Day Care" + ")";
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

                break;

            case R.id.edit_detail:
                break;
        }
    }


    private void checkIntent() {
        if (getIntent() != null) {
            careData = getIntent().getExtras().getParcelable(ApiConstant.DAYCARE_ID);

            detailBinding.overviewDesc.setText(careData.getDaycare_short_description()+"\n"+
                    careData.getDaycare_long_description());
            detailBinding.dayCareName.setText(careData.getDaycare_name());
            detailBinding.dayCareLocation.setText("Location "+careData.getDaycare_address());
            detailBinding.maxAge.setText(careData.getDaycare_child_age_min_value()+"year - "+
                    careData.getDaycare_child_age_max_value()+"year");

            detailBinding.maxFees.setText(careData.getDaycare_budget_min_value()+ " - "+
                    careData.getDaycare_budget_max_value());

            detailBinding.timingValue.setText(careData.getDaycare_open_hours().get(0).getDaycare_open_time()+
                    " "+careData.getDaycare_open_hours().get(0).getDaycare_close_time());

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

         }
    }

}
