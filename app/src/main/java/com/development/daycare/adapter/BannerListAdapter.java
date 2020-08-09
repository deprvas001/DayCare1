package com.development.daycare.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.daycare.R;
import com.development.daycare.constant.ApiConstant;
import com.development.daycare.model.addBanner.BannerResponseListData;
import com.development.daycare.util.Util;
import com.development.daycare.views.activity.DayCareDetail;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerListAdapter extends RecyclerView.Adapter<BannerListAdapter.MyViewHolder>  {
    private List<BannerResponseListData> bannerList;
    private Context context;
    Dialog dialog;

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


    public BannerListAdapter(Context context,List<BannerResponseListData> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    @Override
    public BannerListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_list_row, parent, false);

        return new BannerListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BannerListAdapter.MyViewHolder holder, int position) {
        BannerResponseListData careData = bannerList.get(position);
        holder.name.setText(careData.getDaycare_banner_label());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showImageDialog1(careData.getDaycare_banner_url());
            }
        });

        Picasso.get()
                .load(careData.getDaycare_banner_url())
                // .placeholder(R.drawable.image1)
                //   .error(R.drawable.err)
                .into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    public void showImageDialog1(String image) {
        // custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.property_view);

        // set the custom dialog components - text, image and button
        ImageButton close = (ImageButton) dialog.findViewById(R.id.btnClose);
        PhotoView imageView = dialog.findViewById(R.id.photo_view);

        Util.loadImage(imageView, image,
                Util.getCircularDrawable(context));

        // Close Button
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //TODO Close button action
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }

}
