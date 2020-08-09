package com.development.daycare.views.activity.dayCareBanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.development.daycare.session.SessionManager;
import com.development.daycare.views.activity.BaseActivity;
import com.development.daycare.views.activity.dayCareAdd.AddDayCareSecond;
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
    SessionManager session;
    String token,day_care_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBinding = DataBindingUtil.setContentView(this,R.layout.activity_show_banner_list);
        checkIntent();
        setClickListener();
    }

    private void setClickListener(){
        listBinding.back.setOnClickListener(this);
        listBinding.addMore.setOnClickListener(this);
    }

    private void getDayCareList(String daycare_id) {
        showProgressDialog(getString(R.string.loading));
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DAYCARE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICATE_TOKEN, token);


        viewModel = ViewModelProviders.of(this).get(AddBannerViewModel.class);
        viewModel.getBannerList(this, headers, "0", "10",daycare_id).observe(this, new Observer<BannerListApiResponse>() {
            @Override
            public void onChanged(BannerListApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    if (apiResponse.getResponse().getStatus() == 1) {
                        if(apiResponse.getResponse().getData().size()>0){
                            setRecyclerview(apiResponse.getResponse().getData());
                        }else{
                            Toast.makeText(ShowBannerList.this, "No Data.", Toast.LENGTH_SHORT).show();
                        }

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

            case R.id.add_more:
                Intent intent = new Intent(ShowBannerList.this, DayCareBanner.class);
                intent.putExtra(ApiConstant.DAYCARE_ID,day_care_id);
                intent.putExtra(ApiConstant.DAY_CARE_SHOW,true);
                startActivity(intent);
                break;

        }
    }

    private void checkIntent(){
        if(getIntent().getExtras()!=null){

         String daycare_id = getIntent().getExtras().getString(ApiConstant.DAYCARE_ID);

         getSession(daycare_id);

        }
    }

    private void setRecyclerview(List<BannerResponseListData> careDataList) {
        adapter = new BannerListAdapter(this, careDataList);
        mLayoutManager = new LinearLayoutManager(this);
        listBinding.recyclerView.setLayoutManager(mLayoutManager);
        listBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        listBinding.recyclerView.setAdapter(adapter);
    }

    private void getSession(String daycare_id) {
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
        day_care_id = daycare_id;
        getDayCareList(daycare_id);

    }
}
