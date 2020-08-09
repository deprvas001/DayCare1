package com.development.daycare.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.development.daycare.R;
import com.development.daycare.adapter.ZoomImageAdapter;
import com.development.daycare.databinding.ActivityZoomImageBinding;
import com.development.daycare.model.addDay.DayCareBannerList;
import com.development.daycare.model.homeModel.HomeSlider;

import java.util.ArrayList;

public class ZoomImage extends AppCompatActivity {
    ActivityZoomImageBinding imageBinding;
    ArrayList<DayCareBannerList> sliderList;
    private ZoomImageAdapter communityAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageBinding = DataBindingUtil.setContentView(this,R.layout.activity_zoom_image);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (getIntent().getExtras() != null) {
            sliderList = getIntent().getParcelableArrayListExtra("image_list");
            setRecyclerView(sliderList);

        }
    }

    public void setRecyclerView(ArrayList<DayCareBannerList> sliderList) {
        communityAdapter = new ZoomImageAdapter(this, sliderList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        imageBinding.recyclerView.setLayoutManager(mLayoutManager);
        imageBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        imageBinding.recyclerView.setAdapter(communityAdapter);
    }
}
