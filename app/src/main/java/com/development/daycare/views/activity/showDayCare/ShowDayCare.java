package com.development.daycare.views.activity.showDayCare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.adapter.BookmarkAdapter;
import com.development.daycare.adapter.ShowCareAdapter;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityShowDayCareBinding;
import com.development.daycare.model.BookmarkData;
import com.development.daycare.model.showCareModel.ShowCareApiResponse;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.dayCareAdd.AddCareViewModel;
import com.development.daycare.views.activity.dayCareAdd.AddDayCareSecond;
import com.development.daycare.views.activity.daycareActivities.CareViewModel;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayCareBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_day_care);
        setClickListener();
    }

    private void setClickListener() {
        dayCareBinding.back.setOnClickListener(this);
        // getSession();
        getDayCareList();
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
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTg5MzU0MTE0LCJuYmYiOjE1ODkzNTQxMTQsImV4cCI6MTU5MDU2MzcxNCwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJEQVlDQVJFIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiIxMTExQGdtYWlsLmNvbSIsInVzZXJfaWQiOiI5MCIsInVzZXJfbG9nX2lkIjo0NX19.j5-31UujgKd01b9OtvXAakLqbn-y9CVaqImnDU2OQrA");


        viewModel = ViewModelProviders.of(this).get(ShowCareViewModel.class);
        viewModel.getCareList(this, headers, "0", "10").observe(this, new Observer<ShowCareApiResponse>() {
            @Override
            public void onChanged(ShowCareApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    if (apiResponse.getResponse().getStatus() == 1) {
                        careDataList.add(apiResponse.getResponse().getData());
                        setRecyclerview(careDataList);
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

}
