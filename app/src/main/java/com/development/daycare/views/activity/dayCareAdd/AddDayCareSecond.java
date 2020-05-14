package com.development.daycare.views.activity.dayCareAdd;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityAddDayCareSecondTwoBinding;
import com.development.daycare.model.addDay.AddCareApiResponse;
import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.model.addDay.CareOpenTime;
import com.development.daycare.model.addDay.DayCareInfo;
import com.development.daycare.model.addDay.DayCareMenu;
import com.development.daycare.model.addDay.SubjectList;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.dayCareBanner.DayCareBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDayCareSecond extends BaseActivity implements View.OnClickListener {
    ActivityAddDayCareSecondTwoBinding careSecondTwoBinding;
    AddCareRequest careRequest = new AddCareRequest();
    DayCareMenu careMenu = new DayCareMenu();
    DayCareInfo dayCareInfo = new DayCareInfo();
    SubjectList subject = new SubjectList();
    CareOpenTime openTime = new CareOpenTime();
    List<DayCareMenu> careMenuList = new ArrayList();
    List<DayCareInfo> careInfoList = new ArrayList<>();
    List<SubjectList> subjectLists = new ArrayList<>();
    List<CareOpenTime> openTimeList = new ArrayList<>();
    AddCareViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        careSecondTwoBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_day_care_second_two);
        checkIntent();

    }

    private void checkIntent() {
        if (getIntent() != null) {
            careRequest = getIntent().getExtras().getParcelable(ApiConstant.DAY_ONE);
            Toast.makeText(this, careRequest.getDaycare_name(), Toast.LENGTH_SHORT).show();

            careSecondTwoBinding.setAddCare(careRequest);
            careSecondTwoBinding.setDayCareMenu(careMenu);
            careSecondTwoBinding.setDayCareInfo(dayCareInfo);
            careSecondTwoBinding.setSubject(subject);
            careSecondTwoBinding.setOpenTime(openTime);

            setClickListener();
        }
    }


    private void setClickListener() {
        careSecondTwoBinding.back.setOnClickListener(this);
        careSecondTwoBinding.btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                if (careSecondTwoBinding.inputMealType.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.meal_type_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.inputMealName.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.meal_name_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.menuDescription.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.meal_description_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.menuDescription.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.meal_description_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.contactLabel.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.contact_label_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.contactName.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.contact_name_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.contactEmail.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.contact_email_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.contactPhone.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.contact_phone_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.alternateContact.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.contact_alternate_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.openTime.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.open_time_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.closeTime.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.close_time_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.timeDescription.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.time_description_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.subjectName.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.subject_name_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else if (careSecondTwoBinding.subjectDescription.getText().toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.subject_description_empty), Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    addDayRequest();
                }


                break;

            case R.id.back:
                finish();
                break;
        }
    }

    private void addDayRequest() {
        openTime.setDaycare_day_id("1");

        careMenuList.add(careMenu);
        careInfoList.add(dayCareInfo);
        subjectLists.add(subject);
        openTimeList.add(openTime);


        careRequest.setMeal_menu_list(careMenuList);
        careRequest.setContact_info(careInfoList);
        careRequest.setSubject_list(subjectLists);
        careRequest.setOpening_time(openTimeList);
        //     Toast.makeText(this, careRequest.getMeal_menu_list().get(0).getMenu_name(), Toast.LENGTH_SHORT).show();
        addDayCare();
    }

    private void addDayCare() {
        showProgressDialog(getString(R.string.loading));

        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTg5MTI2MzIzLCJuYmYiOjE1ODkxMjYzMjMsImV4cCI6MTU5MDMzNTkyMywiZGF0YSI6eyJ1c2VyX3R5cGUiOiJEQVlDQVJFIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiIxMjFAZ21haWwuY29tIiwidXNlcl9pZCI6Ijg5IiwidXNlcl9sb2dfaWQiOjQzfX0.oZHnTt1fC75yB1V_Q7XoWpPKmXVBMIRtfrJJ9bpwVtA");


        viewModel = ViewModelProviders.of(this).get(AddCareViewModel.class);
        viewModel.addDayCare(this, headers, careRequest).observe(this, new Observer<AddCareApiResponse>() {
            @Override
            public void onChanged(AddCareApiResponse apiResponse) {
                hideProgressDialog();
              if (apiResponse.response != null) {
                    Toast.makeText(AddDayCareSecond.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    if (apiResponse.getResponse().getStatus() ==1) {
                        if(apiResponse.getResponse().getData().getStatus() ==1){
                          String day_care_id = apiResponse.getResponse().getData().getData().getDaycare_id();
                           goGalleryScreen(day_care_id);
                        }
                    }
                } else {
                    // call failed.
                    Toast.makeText(AddDayCareSecond.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goGalleryScreen(String day_care_id) {
        Intent intent = new Intent(AddDayCareSecond.this, DayCareBanner.class);
        intent.putExtra(ApiConstant.DAYCARE_ID,day_care_id);
        startActivity(intent);
    }

}
