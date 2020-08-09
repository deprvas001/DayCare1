package com.development.daycare.views.activity.dayCareAdd;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
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
import com.development.daycare.session.SessionManager;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.DayCareDetail;
import com.development.daycare.views.activity.PartnerCareHome;
import com.development.daycare.views.activity.dayCareBanner.DayCareBanner;
import com.development.daycare.views.activity.daycareActivities.DayCareActivity;
import com.development.daycare.views.activity.daycarelist.DayCareList;
import com.development.daycare.views.activity.showDayCare.ShowDayCare;

import java.util.ArrayList;
import java.util.Calendar;
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
    SessionManager session;
    String token;
    Boolean isEdit=false;
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
        if (getIntent().getExtras() != null) {
            careRequest = getIntent().getExtras().getParcelable(ApiConstant.DAY_ONE);
          //  Toast.makeText(this, careRequest.getDaycare_name(), Toast.LENGTH_SHORT).show();

            careSecondTwoBinding.setAddCare(careRequest);
            careSecondTwoBinding.setDayCareMenu(careMenu);
            careSecondTwoBinding.setDayCareInfo(dayCareInfo);
            careSecondTwoBinding.setSubject(subject);
            careSecondTwoBinding.setOpenTime(openTime);

            if(careRequest.getContact_info() !=null){
                dayCareInfo.setContact_label(careRequest.getContact_info().get(0).getContact_label());
                dayCareInfo.setContact_name(careRequest.getContact_info().get(0).getContact_name());
                dayCareInfo.setContact_email(careRequest.getContact_info().get(0).getContact_email());
                dayCareInfo.setContact_phone(careRequest.getContact_info().get(0).getContact_phone());
                dayCareInfo.setContact_alternate_phone(careRequest.getContact_info().get(0).getContact_alternate_phone());

                isEdit =true;
            }

            if(careRequest.getMeal_menu_list() !=null){
                careMenu.setMenu_name(careRequest.getMeal_menu_list().get(0).getMenu_name());
                careMenu.setMenu_description(careRequest.getMeal_menu_list().get(0).getMenu_description());
            }

            if(careRequest.getSubject_list() !=null){
                subject.setSubject_name(careRequest.getSubject_list().get(0).getSubject_name());
                subject.setSubject_description(careRequest.getSubject_list().get(0).getSubject_description());
            }

            if(careRequest.getOpening_time()!=null){
                openTime.setOpen_time(careRequest.getOpening_time().get(0).getOpen_time());
                openTime.setClose_time(careRequest.getOpening_time().get(0).getClose_time());
                openTime.setExtra_info(careRequest.getOpening_time().get(0).getExtra_info());
            }



            setClickListener();
        }
    }


    private void setClickListener() {
        careSecondTwoBinding.back.setOnClickListener(this);
        careSecondTwoBinding.btnNext.setOnClickListener(this);
        careSecondTwoBinding.openTime.setOnClickListener(this);
        careSecondTwoBinding.closeTime.setOnClickListener(this);
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
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(careSecondTwoBinding.contactEmail.getText().toString()).matches()) {
                        addDayRequest();
                    }else{
                        Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
                        break;
                    }

                }


                break;

            case R.id.back:
                finish();
                break;

            case R.id.open_time:
                showHourPicker("1");
                break;

            case R.id.close_time:
                showHourPicker("2");
                break;

        }
    }

    private void addDayRequest() {
        openTime.setDay_id("1");


        careMenuList.add(careMenu);
        careInfoList.add(dayCareInfo);
        subjectLists.add(subject);
        openTimeList.add(openTime);


        careRequest.setMeal_menu_list(careMenuList);
        careRequest.setContact_info(careInfoList);
        careRequest.setSubject_list(subjectLists);
        careRequest.setOpening_time(openTimeList);
        //     Toast.makeText(this, careRequest.getMeal_menu_list().get(0).getMenu_name(), Toast.LENGTH_SHORT).show();
       getSession();
    }

    private void addDayCare() {
        showProgressDialog(getString(R.string.loading));

        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, token);


        viewModel = ViewModelProviders.of(this).get(AddCareViewModel.class);
        viewModel.addDayCare(this, headers, careRequest).observe(this, new Observer<AddCareApiResponse>() {
            @Override
            public void onChanged(AddCareApiResponse apiResponse) {
                hideProgressDialog();
              if (apiResponse.response != null) {
                    Toast.makeText(AddDayCareSecond.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    if (apiResponse.getResponse().getStatus() ==1) {

                        if(!isEdit){
                            if(apiResponse.getResponse().getData().getStatus() ==1){
                                String day_care_id = apiResponse.getResponse().getData().getData().getDaycare_id();
                                goGalleryScreen(day_care_id);
                            }
                        }else{
                            Intent i = new Intent(AddDayCareSecond.this, ShowDayCare.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                            startActivity(i);
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
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(intent);
       // finish();
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

        addDayCare();
    }


    public void showHourPicker(String id) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddDayCareSecond.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if(id.equals("1")){
                    careSecondTwoBinding.openTime.setText( selectedHour + ":" + selectedMinute);
                }else{
                    careSecondTwoBinding.closeTime.setText( selectedHour + ":" + selectedMinute);
                }

            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
