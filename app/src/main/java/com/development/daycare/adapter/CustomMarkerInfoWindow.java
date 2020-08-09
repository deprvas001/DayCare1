package com.development.daycare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.development.daycare.R;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomMarkerInfoWindow implements GoogleMap.InfoWindowAdapter {

    private Context context;
    public CustomMarkerInfoWindow(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.marker_info_window, null);
        ShowCareData careData =(ShowCareData) marker.getTag();
        //  LatLng latLng = arg0.getPosition();
        TextView date = (TextView) v.findViewById(R.id.date);
        TextView latlong = (TextView)v.findViewById(R.id.lat_long);
        TextView address = (TextView)v.findViewById(R.id.address);
        TextView device = (TextView)v.findViewById(R.id.name);
        TextView shipment_number = (TextView)v.findViewById(R.id.shipment_number);
        TextView tvLng = (TextView) v.findViewById(R.id.lat_long);
        device.setText(careData.getDaycare_name());
        latlong.setText(careData.getDaycare_address());
        address.setText(careData.getDaycare_short_description());
        latlong.setText(careData.getDaycare_latitude());

         tvLng.setText(careData.getDaycare_latitude() +" , " +careData.getDaycare_longitude());
        return v;
    }

}
