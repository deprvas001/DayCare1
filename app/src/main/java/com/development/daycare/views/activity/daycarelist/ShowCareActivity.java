package com.development.daycare.views.activity.daycarelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.adapter.CareListAdapter;
import com.development.daycare.adapter.ShowActivityAdapter;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityShowCareBinding;
import com.development.daycare.model.addDay.InHouseActivity;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ShowCareActivity extends AppCompatActivity {
    ActivityShowCareBinding careBinding;
    private ShowActivityAdapter activityAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<InHouseActivity> inHouseActivityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        careBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_care);
        checkIntent();
    }

    private void setRecyclerView(List<InHouseActivity> dataList) {
        activityAdapter = new ShowActivityAdapter(this, dataList);
        mLayoutManager = new LinearLayoutManager(this);
        careBinding.recyclerView.setLayoutManager(mLayoutManager);
        careBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        careBinding.recyclerView.setAdapter(activityAdapter);

    }

    private void checkIntent() {
        if (getIntent() != null) {

            if (getIntent().getExtras().containsKey(ApiConstant.DAY_CARE_ACTIVITY)) {
                inHouseActivityList = getIntent().getExtras().getParcelableArrayList(ApiConstant.DAY_CARE_ACTIVITY);
                if(inHouseActivityList.size()>0){
                    setRecyclerView(inHouseActivityList);
                }else{
                    Toast.makeText(this, "No Data Found.", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

}