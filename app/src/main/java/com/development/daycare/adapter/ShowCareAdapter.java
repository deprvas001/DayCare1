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
import com.development.daycare.views.activity.DayCareDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowCareAdapter extends RecyclerView.Adapter<ShowCareAdapter.MyViewHolder> {

    private List<ShowCareData> bookmarkList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,age,resident,city,university,address,status;
        public ImageView logo;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView)view.findViewById(R.id.cardView);
            name = (TextView)view.findViewById(R.id.name);
            address = (TextView)view.findViewById(R.id.address);
            logo = (ImageView)view.findViewById(R.id.imageView);
            status = (TextView)view.findViewById(R.id.status);

        }
    }


    public ShowCareAdapter(Context context,List<ShowCareData> bookmarkList) {
        this.context = context;
        this.bookmarkList = bookmarkList;
    }

    @Override
    public ShowCareAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_day_care, parent, false);

        return new ShowCareAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowCareAdapter.MyViewHolder holder, int position) {
        ShowCareData careData = bookmarkList.get(position);
        holder.name.setText(careData.getDaycare_name());
        holder.address.setText(careData.getDaycare_address());
        if(careData.getDaycare_status().equals("0")){
            holder.status.setText("Not Publish");
            holder.status.setTextColor(context.getResources().getColor(R.color.red));

        }if(careData.getDaycare_status().equals("1")){
            holder.status.setTextColor(context.getResources().getColor(R.color.green));
            holder.status.setText("Publish");
        }

//        holder.address.setText(bookmark.getAddress());
//        holder.resident.setText(bookmark.getCountry());
        /*holder.city.setText(bookmark.getCity_name());
        holder.university.setText(bookmark.getUniversity_name());*/


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DayCareDetail.class);
                intent.putExtra(ApiConstant.DAYCARE_ID,careData);
                context.startActivity(intent);
            }
        });

        Picasso.get()
                .load(careData.getDaycare_logo_url())
                // .placeholder(R.drawable.image1)
                //   .error(R.drawable.err)
                .into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }
}
