package com.development.daycare.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.development.daycare.R;
import com.development.daycare.databinding.ActivityPartnerCareHomeBinding;
import com.development.daycare.views.activity.dayCareAdd.AddDayCare;
import com.development.daycare.views.activity.partnerProfile.PartnerProfile;

public class PartnerCareHome extends AppCompatActivity implements View.OnClickListener {
ActivityPartnerCareHomeBinding careHomeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        careHomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_partner_care_home);
        setClickListener();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_profile:
                startActivity(new Intent(PartnerCareHome.this, PartnerProfile.class));
                break;

            case R.id.btn_add:
                startActivity(new Intent(PartnerCareHome.this, AddDayCare.class));
                break;

            case R.id.back:
                finish();
                 break;
        }
    }

    private void setClickListener(){
        careHomeBinding.btnProfile.setOnClickListener(this);
        careHomeBinding.back.setOnClickListener(this);
        careHomeBinding.btnAdd.setOnClickListener(this);
    }
}
