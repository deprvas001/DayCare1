package com.development.daycare.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.development.daycare.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PartnerHomeNavigation extends BaseActivity implements View.OnClickListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                 /*   fragment = new Home();
                    loadFragment(fragment);*/
                    return true;

                case R.id.navigation_profile:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                 /*   fragment = new Home();
                    loadFragment(fragment);*/
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_home_navigation);
    }

    @Override
    public void onClick(View view) {

    }
}
