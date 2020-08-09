package com.development.daycare.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.development.daycare.R;
import com.development.daycare.model.addDay.DayCareBannerList;
import com.development.daycare.util.Util;
import com.development.daycare.views.activity.ZoomImage;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DayCareBannerAdapter extends
        SliderViewAdapter< DayCareBannerAdapter.DayCareBannerAdapterVH> {

    private Context context;
    private int mCount;
    private List<DayCareBannerList> sliderList;

    public DayCareBannerAdapter(Context context, List<DayCareBannerList> sliderList) {
        this.context = context;
        this.sliderList = sliderList;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public DayCareBannerAdapter.DayCareBannerAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout, null);
        return new DayCareBannerAdapter.DayCareBannerAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(DayCareBannerAdapter.DayCareBannerAdapterVH viewHolder, final int position) {


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context , ZoomImage.class);
                intent.putParcelableArrayListExtra("image_list", (ArrayList<? extends Parcelable>) sliderList);
                context.startActivity(intent);
            }
        });

        DayCareBannerList slider = sliderList.get(position);
        viewHolder.textViewDescription.setText(slider.getBanner_label());

        Util.loadImage(viewHolder.imageViewBackground,slider.getBanner_url() ,
                Util.getCircularDrawable(context));


    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class DayCareBannerAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public DayCareBannerAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
