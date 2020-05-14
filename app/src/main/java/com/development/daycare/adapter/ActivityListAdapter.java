package com.development.daycare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.daycare.R;
import com.development.daycare.model.addCareActivity.ActivityListData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.MyViewHolder> {

    private List<ActivityListData> bannerList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView logo;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView)view.findViewById(R.id.cardView);
            name = (TextView)view.findViewById(R.id.name);
            logo = (ImageView)view.findViewById(R.id.image);

        }
    }


    public ActivityListAdapter(Context context,List<ActivityListData> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    @Override
    public ActivityListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_list_row, parent, false);

        return new ActivityListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ActivityListAdapter.MyViewHolder holder, int position) {
        ActivityListData listData = bannerList.get(position);
        holder.name.setText(listData.getDaycare_activity_name());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Picasso.get()
                .load(listData.getDaycare_activity_image())
                // .placeholder(R.drawable.image1)
                //   .error(R.drawable.err)
                .into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }
}
