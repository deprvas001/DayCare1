package com.development.daycare.views.activity.daycarelist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.adapter.BookmarkAdapter;
import com.development.daycare.adapter.DayCareListAdapter;
import com.development.daycare.databinding.ActivityDayCareDetailBinding;
import com.development.daycare.databinding.ActivityDayCareListBinding;
import com.development.daycare.model.BookmarkData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DayCareList extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {
    private ActivityDayCareListBinding listBinding;
    private GoogleMap mMap;
   /* DayCareListAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;*/
    LocationManager locationManager;
    LocationListener locationListener;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_care_list);
      //  recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
       // setData();
      //  listBinding = DataBindingUtil.setContentView(this,R.layout.activity_day_care_list);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //configureCameraIdle();


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
        mMap.setOnMarkerDragListener(this);
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationListener =  new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();
                Log.i("Current Location",location.toString());

                LatLng user_location = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(user_location).title("Marker")
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))


                );
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                //  mMap.setMaxZoomPreference(10);
            //       mMap.moveCamera(CameraUpdateFactory.newLatLng(user_location));


                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try{
                    List<Address> listAddress = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                    if(listAddress !=null && listAddress.size()>0){
                        Log.i("PlaceInfo",listAddress.get(0).toString());

                        String address="";
                        if(listAddress.get(0).getAdminArea() !=null){
                            address+= listAddress.get(0).getAdminArea()+" ";
                        }

                        if(listAddress.get(0).getLocality()!=null){
                            address+= listAddress.get(0).getLocality()+" ";
                        }

                        if(listAddress.get(0).getThoroughfare()!=null){
                            address+= listAddress.get(0).getThoroughfare();
                        }

                   //     Toast.makeText(DayCareList.this, address, Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                   mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user_location, 12.0f));
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

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,60000,0,locationListener);
            Location lastKnowLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            mMap.clear();
            Log.i("Current Location",lastKnowLocation.toString());

            LatLng user_location = new LatLng(lastKnowLocation.getLatitude(), lastKnowLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(user_location).title("Marker")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))

            );
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            //  mMap.setMaxZoomPreference(10);
          //     mMap.moveCamera(CameraUpdateFactory.newLatLng(user_location));

          mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user_location, 12.0f));

        }

    }

  /*  private void setRecyclerview(List<BookmarkData> bookmarkDataList){
        adapter = new DayCareListAdapter(this, bookmarkDataList);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    private void setData(){
        List<BookmarkData> bookmarkList = new ArrayList<>();

        for(int i=0;i<6;i++){
            BookmarkData bookmark = new BookmarkData();
            bookmark.setName("Day Care Name");
            bookmark.setAge("3-4 years");
            bookmark.setCountry("India");
            bookmark.setCity("Delhi");
            bookmark.setImage_url("https://cdn.britannica.com/24/141224-050-0F5FA19C/Caregivers-children-day-care-centre.jpg");
            bookmark.setAddress("9/55 Caroline Street,South Yarra VIC");
            bookmarkList.add(bookmark);
        }

        setRecyclerview(bookmarkList);


    }*/

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,60000,0,locationListener);
            }

        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        Log.d("System out", "onMarkerDragStart..."+marker.getPosition().latitude+"..."+marker.getPosition().longitude);

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Log.d("System out", "onMarkerDragEnd..."+marker.getPosition().latitude+"..."+marker.getPosition().longitude);

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Log.i("System out", "onMarkerDrag...");
    }
}
