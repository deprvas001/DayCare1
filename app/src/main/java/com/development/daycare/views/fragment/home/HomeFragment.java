package com.development.daycare.views.fragment.home;


import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.daycare.R;
import com.development.daycare.adapter.HomeAdapter;
import com.development.daycare.adapter.HomeMenuAdapter;
import com.development.daycare.adapter.HomeSlideAdapter;
import com.development.daycare.databinding.FragmentDayHomeBinding;
import com.development.daycare.model.homeModel.HomeApiResponse;
import com.development.daycare.model.homeModel.HomeSlider;
import com.development.daycare.model.homeModel.MenuList;
import com.development.daycare.views.activity.HomeScreen;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
FragmentDayHomeBinding homeBinding;
HomeViewModel homeViewModel;
private HomeAdapter homeAdapter;
private HomeMenuAdapter menuAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_day_home, container, false);
        getData();

       return  homeBinding.getRoot();
    }


    private void setSliderAndView(List<HomeSlider> sliderList ,
                                  List<HomeSlider> topStorieList,List<MenuList> menuLists ) {
        final HomeSlideAdapter adapter = new HomeSlideAdapter(getActivity(), sliderList);
        adapter.setCount(sliderList.size());

        homeBinding.imageSlider.setSliderAdapter(adapter);

        homeBinding.imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        homeBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        homeBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        homeBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        homeBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        homeBinding.imageSlider.startAutoCycle();

        homeBinding.imageSlider.setOnIndicatorClickListener(position ->
                homeBinding.imageSlider.setCurrentPagePosition(position));

        setRecycleView(topStorieList,menuLists);

    }

    private void getData() {
        ((HomeScreen) getActivity()).showProgressDialog(getResources().getString(R.string.loading));
        String type = "ALL";

        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

        homeViewModel.getData(getActivity(), type).observe(getActivity(), new Observer<HomeApiResponse>() {
            @Override
            public void onChanged(HomeApiResponse homeApiResponse) {
                ((HomeScreen) getActivity()).hideProgressDialog();

                if (homeApiResponse.response != null) {

                   List<HomeSlider> homeSliderList = homeApiResponse.getResponse().getData().getADVERTISEMENTLIST();
                   List<HomeSlider> topStorieList = homeApiResponse.getResponse().getData().getTOPSTORIESLIST();
                   List<MenuList> menuLists = homeApiResponse.getResponse().getData().getMenuList();
                   //  setReyclerView(featurePropertyList, hotPropertyList, homePropertyAreaList, sliderList,countData);
                    setSliderAndView(homeSliderList,topStorieList,menuLists);
                }
            }
        });
    }

    private void setRecycleView(List<HomeSlider> topStorieList,List<MenuList> menuLists ){
        homeAdapter = new HomeAdapter(getActivity(), topStorieList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        homeBinding.recyclerView.setLayoutManager(mLayoutManager);
        homeBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerView.setAdapter(homeAdapter);


        menuAdapter = new HomeMenuAdapter(getActivity(), menuLists);
        mLayoutManager = new GridLayoutManager(getContext(), 4);
        homeBinding.recyclerViewMenu.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewMenu.setAdapter(menuAdapter);
    }


}
