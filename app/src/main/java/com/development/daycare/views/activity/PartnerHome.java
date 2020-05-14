package com.development.daycare.views.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.development.daycare.R;
import com.development.daycare.databinding.ActivityPartnerHomeBinding;
import com.development.daycare.views.fragment.addDayCare.AddDayCare;
import com.development.daycare.views.fragment.partner_fragment.PartnerHomeFragment;
import com.development.daycare.views.fragment.partner_profile.ParnterProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class PartnerHome extends AppCompatActivity {
ActivityPartnerHomeBinding homeBinding;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    fragment = new PartnerHomeFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_dashboard:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    fragment = new AddDayCare();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_notifications:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    fragment = new PartnerHomeFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_profile:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    fragment = new ParnterProfile();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this,R.layout.activity_partner_home);
        initializeView();
        loadFragment(new PartnerHomeFragment());
    }

    private void loadFragment(Fragment fragment) {
        //load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        // transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initializeView(){
        homeBinding.appBar.homeScreen.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
