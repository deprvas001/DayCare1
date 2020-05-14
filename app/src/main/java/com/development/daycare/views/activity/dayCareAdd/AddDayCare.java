package com.development.daycare.views.activity.dayCareAdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityAddDayCareBinding;
import com.development.daycare.model.addDay.AddCareRequest;

public class AddDayCare extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
ActivityAddDayCareBinding careBinding;
AddCareRequest careRequest = new AddCareRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        careBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_day_care);
        careBinding.setAddCare(careRequest);
        setClickListener();
    }

    private void setClickListener(){
        careBinding.btnNext.setOnClickListener(this);
        careBinding.back.setOnClickListener(this);
        careBinding.radioCctv.setOnCheckedChangeListener(this);
        careBinding.radioType.setOnCheckedChangeListener(this);
        careBinding.radioGroupTransport.setOnCheckedChangeListener(this);
        careRequest.setDaycare_cctv_value("1");
        careRequest.setTransport_required("1");
        careRequest.setDaycare_type("0");
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
        careRequest.setDaycare_logo_url("Url.png");
        careRequest.setDaycare_latitude("1233.343");
        careRequest.setDaycare_longitude("332432.324");
       Intent intent = new Intent(AddDayCare.this,AddDayCareSecond.class);
       intent.putExtra(ApiConstant.DAY_ONE,careRequest);
       startActivity(intent);
    }
}
