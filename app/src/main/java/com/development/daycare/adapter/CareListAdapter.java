package com.development.daycare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.model.showCareModel.ShowCareData;
import com.development.daycare.util.Util;
import com.development.daycare.views.activity.DayCareDetail;
import com.development.daycare.views.activity.daycarelist.ShowDayCareInformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CareListAdapter extends RecyclerView.Adapter<CareListAdapter.MyViewHolder> {

    private List<ShowCareData> careList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,age,resident,city,university,address,distance;
        public ImageView image_view,fav_icon;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView)view.findViewById(R.id.cardView);
            name = (TextView)view.findViewById(R.id.name);
            image_view = (ImageView)view.findViewById(R.id.imageView);
            address = (TextView)view.findViewById(R.id.address);
            distance = (TextView)view.findViewById(R.id.distance);

        }
    }


    public CareListAdapter(Context context,List<ShowCareData> careList) {
        this.context = context;
        this.careList = careList;
    }

    @Override
    public CareListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_care_list_row, parent, false);

        return new CareListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CareListAdapter.MyViewHolder holder, int position) {
        ShowCareData daycare = careList.get(position);
        holder.name.setText(daycare.getDaycare_name());
        holder.address.setText(daycare.getDaycare_address());


        holder.distance.setText(daycare.getDaycare_distance()+"km");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ShowDayCareInformation.class);
                intent.putExtra(ApiConstant.DAYCARE_ID,daycare);
                context.startActivity(intent);
            }
        });

        Util.loadImage(holder.image_view,daycare.getDaycare_logo_url() ,
                Util.getCircularDrawable(context));

    }

    @Override
    public int getItemCount() {
        return careList.size();
    }
}
