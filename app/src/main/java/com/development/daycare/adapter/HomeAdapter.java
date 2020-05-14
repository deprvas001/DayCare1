package com.development.daycare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.daycare.R;
import com.development.daycare.model.homeModel.HomeSlider;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<HomeSlider> storieList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,price,address,name,rating,created_date;
        public ImageView imageView;
        LinearLayout viewLayout;

        public MyViewHolder(View view) {
            super(view);

            imageView = (ImageView)view.findViewById(R.id.image);
        }
    }


    public HomeAdapter(Context context,List<HomeSlider> storieList) {
        this.context = context;
        this.storieList = storieList;
    }

    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_storie_layout, parent, false);

        return new HomeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position) {
        HomeSlider top_stories = storieList.get(position);

       /* holder.name.setText(featureProperty.getPropertyName());
        holder.rating.setText(featureProperty.getRating());
        holder.created_date.setText(featureProperty.getCreatedDate());*/

        Picasso.get()
                .load(top_stories.getImage())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);


      /*  holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RoomDetail.class));
            }
        });*/
        /* holder.title.setText(find_room.getType());*/
    }

    @Override
    public int getItemCount() {
        return storieList.size();
    }
}
