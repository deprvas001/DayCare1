package com.development.daycare.views.activity.daycarelist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.development.daycare.DayCareMarker;
import com.development.daycare.R;
import com.development.daycare.UserLocationMap;
import com.development.daycare.adapter.BookmarkAdapter;
import com.development.daycare.adapter.CareListAdapter;
import com.development.daycare.adapter.DayCareListAdapter;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.databinding.ActivityDayCareDetailBinding;
import com.development.daycare.databinding.ActivityDayCareListBinding;
import com.development.daycare.model.BookmarkData;
import com.development.daycare.model.dayCareList.DayCareListModel;
import com.development.daycare.model.dayCareList.DayCareListPost;
import com.development.daycare.model.filter.FilterPostRequest;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.model.showCareModel.ShowCareResponse;
import com.development.daycare.views.activity.SearchScreen;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DayCareList extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ActivityDayCareListBinding listBinding;
    private GoogleMap mMap;
    private CareListAdapter careAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ShowCareData> careDataList = new ArrayList<>();
    //Location

    Map<String, String> headers;
    DayCareListPost post;
    private CareListViewModel viewModel;
    FilterPostRequest postRequest;
    SupportMapFragment mapFragment;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBinding = DataBindingUtil.setContentView(this, R.layout.activity_day_care_list);

        init();
        getData();

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //configureCameraIdle();
    }

    private void getData() {
        viewModel = ViewModelProviders.of(this).get(CareListViewModel.class);

        observeViewModel();

    }

    private void apiCall() {
        headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);

        post = new DayCareListPost();
        post.setOffset("0");
        post.setPer_page("10");
        post.setDistance("10");
        post.setStartlat("28.459497");
        post.setStartlng("77.026634");

        viewModel.refresh(headers, post);
    }


    private void observeViewModel() {
        viewModel.homeResponse.observe(this, response -> {
            if (response != null) {
                if(response.getStatus() == 1){
                    if(response.getData().size()>0){
                        listBinding.recyclerView.setVisibility(View.VISIBLE);
                        setRecyclerView(response.getData());
                    }
                }else
                    {

                    Toast.makeText(this, "No Result Found.", Toast.LENGTH_SHORT).show();
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

                listBinding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    /*  homeBinding.loadError.setVisibility(View.GONE);*/

                    listBinding.recyclerView.setVisibility(View.GONE);
                }

               /* homeBinding.shimmerViewContainer.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading){
                    homeBinding.loadError.setVisibility(View.GONE);
                    homeBinding.layout.setVisibility(View.GONE);
                }*/
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerDragListener(this);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();

                headers = new HashMap<>();
                headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);

                post = new DayCareListPost();
                post.setOffset("0");
                post.setPer_page("10");
                post.setDistance("5");

                post.setStartlat(String.valueOf(location.getLatitude()));
                post.setStartlng(String.valueOf(location.getLongitude()));

                viewModel.refresh(headers, post);

                post.setStartlat(String.valueOf(location.getLatitude()));
                post.setStartlng(String.valueOf(location.getLongitude()));

                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userLocation).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,14.0f));

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50000, 0, locationListener);
            }
        }

    }


    private void init() {
        //  listBinding.search.setOnClickListener(this);
        listBinding.distance.setText("5");
        listBinding.filter.setOnClickListener(this);
        listBinding.fab.setOnClickListener(this);
        listBinding.search.setOnClickListener(this);

        listBinding.refreshLayout.setOnRefreshListener(() -> {
            listBinding.recyclerView.setVisibility(View.GONE);
            viewModel.refresh(headers, post);
            listBinding.refreshLayout.setRefreshing(false);
            observeViewModel();
        });

    }

    private void setRecyclerView(List<ShowCareData> dataList) {
        listBinding.resultFound.setText("Result Found: " + String.valueOf(dataList.size()));
        careDataList = (ArrayList)dataList;

        careAdapter = new CareListAdapter(this, dataList);
        mLayoutManager = new LinearLayoutManager(this);
        listBinding.recyclerView.setLayoutManager(mLayoutManager);
        listBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        listBinding.recyclerView.setAdapter(careAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filter:
                showCustomDialog();
                break;

            case R.id.search:
                startActivity(new Intent(this, SearchScreen.class));
                break;

            case R.id.fab:
                if(careDataList != null && careDataList.size() > 0 ){
                    Intent intent = new Intent(DayCareList.this, DayCareMarker.class);
                    intent.putParcelableArrayListExtra("marker_list",  careDataList);
                    startActivity(intent);
                 }

                break;
        }
    }


    private void showCustomDialog() {
        postRequest = new FilterPostRequest();
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.filter_custom_dialog, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);


        Button ok = (Button) dialogView.findViewById(R.id.buttonOk);
        ImageView close_dialog = (ImageView) dialogView.findViewById(R.id.close);

        RadioGroup cctv_group = (RadioGroup) dialogView.findViewById(R.id.cctv_group);
        RadioGroup transport_group = (RadioGroup) dialogView.findViewById(R.id.transport_group);
        RadioGroup meal_group = (RadioGroup) dialogView.findViewById(R.id.meal_group);

        cctv_group.setOnCheckedChangeListener(this);
        transport_group.setOnCheckedChangeListener(this);
        meal_group.setOnCheckedChangeListener(this);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postRequest.setOffset("0");
                postRequest.setPer_page("10");
                postRequest.setStartlat("28.459497");
                postRequest.setStartlng("77.026634");
                postRequest.setPrice_min_value("");
                postRequest.setPrice_max_value("");
                postRequest.setAge_min_value("2");
                postRequest.setAge_max_value("4");
                postRequest.setDaycare_type("1");
                postRequest.setDaycare_rating_value("");

                viewModel.filterRefresh(headers, postRequest);
                observeViewModel();
                alertDialog.dismiss();
            }
        });


        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int chekedId) {
        switch (chekedId) {
            case R.id.cctv_yes:
                postRequest.setDaycare_cctv_value("1");
                break;

            case R.id.cctv_no:
                postRequest.setDaycare_cctv_value("0");
                break;

            case R.id.transport_yes:
                postRequest.setDaycare_transportation_required("1");
                break;

            case R.id.transport_no:
                postRequest.setDaycare_transportation_required("0");
                break;

            case R.id.meal_yes:
                postRequest.setDaycare_type_of_meals("1");
                break;

            case R.id.meal_no:
                postRequest.setDaycare_type_of_meals("0");
                break;
        }

    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        /*marker.getPosition();
        LatLng userLatLng = new LatLng(location.getLatitude(),location.getLongitude());*/
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 18f));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50000, 0, locationListener);
                    Location lastknowLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    mMap.clear();
                    LatLng userLocation = new LatLng(lastknowLocation.getLatitude(), lastknowLocation.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(userLocation).title("Marker in Sydney"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
                }
            }
        }
    }
}
