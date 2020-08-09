package com.development.daycare.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.development.daycare.R;
import com.development.daycare.model.homeModel.HomeSlider;
import com.development.daycare.util.Util;
import com.development.daycare.views.activity.ZoomImage;
import com.github.chrisbanes.photoview.PhotoView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeSlideAdapter extends
        SliderViewAdapter< HomeSlideAdapter.HomeSlideAdapterVH> {

    private Context context;
    private int mCount;
    private List<HomeSlider> sliderList;
    Dialog dialog;

    public HomeSlideAdapter(Context context, List<HomeSlider> sliderList) {
        this.context = context;
        this.sliderList = sliderList;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public HomeSlideAdapter.HomeSlideAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout, null);
        return new HomeSlideAdapter.HomeSlideAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(HomeSlideAdapter.HomeSlideAdapterVH viewHolder, final int position) {




        HomeSlider slider = sliderList.get(position);
        viewHolder.textViewDescription.setText(slider.getTitle());
        Picasso.get()
                .load(slider.getImage())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog1(slider.getImage());
            }
        });

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class HomeSlideAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public HomeSlideAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
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
