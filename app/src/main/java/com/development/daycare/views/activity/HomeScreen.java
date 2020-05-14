package com.development.daycare.views.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.development.daycare.R;
import com.development.daycare.databinding.ActivityHomeScreenBinding;
import com.development.daycare.views.fragment.home.HomeFragment;

public class HomeScreen extends BaseActivity {
ActivityHomeScreenBinding screenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenBinding = DataBindingUtil.setContentView(this,R.layout.activity_home_screen);
        loadFragment(new HomeFragment());
    }

    private void loadFragment(Fragment fragment) {
        //load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        // transaction.addToBackStack(null);
        transaction.commit();
    }
}
