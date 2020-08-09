package com.development.daycare.views.activity.showDayCare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.adapter.BookmarkAdapter;
import com.development.daycare.adapter.HomeSlideAdapter;
import com.development.daycare.adapter.ShowCareAdapter;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityShowDayCareBinding;
import com.development.daycare.model.BookmarkData;
import com.development.daycare.model.homeModel.HomeSlider;
import com.development.daycare.model.homeModel.MenuList;
import com.development.daycare.model.showCareModel.ShowCareApiResponse;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.session.SessionManager;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.dayCareAdd.AddCareViewModel;
import com.development.daycare.views.activity.dayCareAdd.AddDayCareSecond;
import com.development.daycare.views.activity.daycareActivities.CareViewModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowDayCare extends BaseActivity implements View.OnClickListener {
    ActivityShowDayCareBinding dayCareBinding;
    ShowCareViewModel viewModel;
    List<ShowCareData> careDataList = new ArrayList<>();
    ShowCareAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    SessionManager session;
   String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayCareBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_day_care);
        setClickListener();
    }

    private void setClickListener() {
        dayCareBinding.back.setOnClickListener(this);
        // getSession();
        getSession();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
        }
    }

    private void getDayCareList() {
        showProgressDialog(getString(R.string.loading));
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, token);


        viewModel = ViewModelProviders.of(this).get(ShowCareViewModel.class);
        viewModel.getCareList(this, headers, "0", "10").observe(this, new Observer<ShowCareApiResponse>() {
            @Override
            public void onChanged(ShowCareApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    if (apiResponse.getResponse().getStatus() == 1) {
                        setRecyclerview(apiResponse.getResponse().getData());
                    }
                } else {
                    // call failed.
                    Toast.makeText(ShowDayCare.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void setRecyclerview(List<ShowCareData> careDataList) {
        adapter = new ShowCareAdapter(this, careDataList);
        mLayoutManager = new LinearLayoutManager(this);
        dayCareBinding.recyclerView.setLayoutManager(mLayoutManager);
        dayCareBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        dayCareBinding.recyclerView.setAdapter(adapter);
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

        getDayCareList();

    }


}
