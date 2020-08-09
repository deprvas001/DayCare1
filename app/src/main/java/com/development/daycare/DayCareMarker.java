package com.development.daycare;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.development.daycare.adapter.CustomMarkerInfoWindow;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.views.activity.daycarelist.ShowDayCareInformation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class DayCareMarker extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    ArrayList<ShowCareData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_care_marker);

        checkIntent();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);

        if(dataList.size()>0) {
            for(int i =0; i<dataList.size();i++){
                Double latitude = Double.parseDouble(dataList.get(i).getDaycare_latitude());
                Double longitude = Double.parseDouble(dataList.get(i).getDaycare_longitude());
                LatLng care_location = new LatLng(latitude, longitude);
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(care_location)
                        .title(dataList.get(i).getDaycare_name()));
                marker.setTag(dataList.get(i));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(care_location,11.0f));
            }
        }



        CustomMarkerInfoWindow infoWindow = new CustomMarkerInfoWindow(this);
        googleMap.setInfoWindowAdapter(infoWindow);



        try {
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.uber_maps_style));

            if (!success) {
                Log.e("Day Care Error", "Style Parse Error");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Day Care Exception ", e.getMessage());
        }
    }


    private void checkIntent(){
        if(getIntent().getExtras() != null){
            if(getIntent().getExtras().containsKey("marker_list")){

                dataList = getIntent().getParcelableArrayListExtra("marker_list");
            }
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        ShowCareData daycare =(ShowCareData) marker.getTag();
        Intent intent = new Intent(this, ShowDayCareInformation.class);
        intent.putExtra(ApiConstant.DAYCARE_ID,daycare);
        startActivity(intent);
    }
}
