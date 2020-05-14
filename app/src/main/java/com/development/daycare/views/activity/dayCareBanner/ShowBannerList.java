package com.development.daycare.views.activity.dayCareBanner;

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
import com.development.daycare.adapter.BannerListAdapter;
import com.development.daycare.adapter.ShowCareAdapter;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityShowBannerListBinding;
import com.development.daycare.model.addBanner.BannerListApiResponse;
import com.development.daycare.model.addBanner.BannerResponseListData;
import com.development.daycare.model.showCareModel.ShowCareApiResponse;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.showDayCare.ShowCareViewModel;
import com.development.daycare.views.activity.showDayCare.ShowDayCare;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowBannerList extends BaseActivity implements View.OnClickListener {
ActivityShowBannerListBinding listBinding;
AddBannerViewModel viewModel;
    BannerListAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBinding = DataBindingUtil.setContentView(this,R.layout.activity_show_banner_list);
        checkIntent();
        setClickListener();
    }

    private void setClickListener(){
        listBinding.back.setOnClickListener(this);
    }

    private void getDayCareList(String daycare_id) {
        showProgressDialog(getString(R.string.loading));
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTg5MzU0MTE0LCJuYmYiOjE1ODkzNTQxMTQsImV4cCI6MTU5MDU2MzcxNCwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJEQVlDQVJFIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiIxMTExQGdtYWlsLmNvbSIsInVzZXJfaWQiOiI5MCIsInVzZXJfbG9nX2lkIjo0NX19.j5-31UujgKd01b9OtvXAakLqbn-y9CVaqImnDU2OQrA");


        viewModel = ViewModelProviders.of(this).get(AddBannerViewModel.class);
        viewModel.getBannerList(this, headers, "0", "10","29").observe(this, new Observer<BannerListApiResponse>() {
            @Override
            public void onChanged(BannerListApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    if (apiResponse.getResponse().getStatus() == 1) {
                       setRecyclerview(apiResponse.getResponse().getData());
                    }else{
                       // Toast.makeText(ShowBannerList.this, apiResponse.getResponse()., Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // call failed.
                    Toast.makeText(ShowBannerList.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

        }
    }

    private void checkIntent(){
        if(getIntent().getExtras()!=null){

         String daycare_id = getIntent().getExtras().getString(ApiConstant.DAYCARE_ID);

         getDayCareList(daycare_id);

        }
    }

    private void setRecyclerview(List<BannerResponseListData> careDataList) {
        adapter = new BannerListAdapter(this, careDataList);
        mLayoutManager = new LinearLayoutManager(this);
        listBinding.recyclerView.setLayoutManager(mLayoutManager);
        listBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        listBinding.recyclerView.setAdapter(adapter);
    }
}
