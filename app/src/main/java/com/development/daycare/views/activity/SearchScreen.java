package com.development.daycare.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.adapter.CareListAdapter;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivitySearchScreenBinding;
import com.development.daycare.model.dayCareList.DayCareListPost;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.views.activity.daycarelist.CareListViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchScreen extends AppCompatActivity implements View.OnClickListener {
    private CareListAdapter careAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ActivitySearchScreenBinding screenBinding;

    private CareListViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenBinding = DataBindingUtil.setContentView(this,R.layout.activity_search_screen);
        init();

    }

    private void init(){
        viewModel = ViewModelProviders.of(this).get(CareListViewModel.class);
        screenBinding.btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
                if(screenBinding.searchEdit.getText().toString().isEmpty()){
                    Toast.makeText(this, "Search Field Empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    screenBinding.progressBar.setVisibility(View.VISIBLE);

                    DayCareListPost post =new DayCareListPost();
                    post.setOffset("0");
                    post.setPer_page("10");
                    post.setStartlat("28.459497");
                    post.setStartlng("77.026634");
                    post.setSearchvalue(screenBinding.searchEdit.getText().toString());

                    Map<String,String> headers = new HashMap<>();
                    headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);

                    viewModel.getSearch(headers,post);

                    observeSearchViewModel();
                }
                break;
        }
    }


    private void observeSearchViewModel() {

        viewModel.homeResponse.observe(this, response -> {
            if (response != null) {
                if(response.getStatus() == 1){
                    if(response.getData().size()>0){
                        setRecyclerView(response.getData());
                        screenBinding.recyclerView.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    screenBinding.recyclerView.setVisibility(View.GONE);
                    screenBinding.resultFound.setText("No Result Found");
                    Toast.makeText(this, "No Result Found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewModel.error.observe(this, isError -> {
            if (isError != null && isError instanceof Boolean) {
               /* homeBinding.loadError.setVisibility(isError ? View.VISIBLE : View.GONE);
                if(isError){
                    homeBinding.layout.setVisibility(View.GONE);
                }*/
            }
        });

        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                screenBinding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading){
                  /*  homeBinding.loadError.setVisibility(View.GONE);*/

                    screenBinding.recyclerView.setVisibility(View.GONE);

                }
            }
        });

    }

    private void setRecyclerView(List<ShowCareData> dataList){
        screenBinding.resultFound.setText("Result Found: "+ String.valueOf(dataList.size()));

        careAdapter = new CareListAdapter(this, dataList);
        mLayoutManager = new LinearLayoutManager(this);
        screenBinding.recyclerView.setLayoutManager(mLayoutManager);
        screenBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        screenBinding.recyclerView.setAdapter(careAdapter);
    }

}
