package com.development.daycare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.daycare.R;
import com.development.daycare.model.addDay.DayCareBannerList;
import com.development.daycare.model.homeModel.HomeSlider;
import com.development.daycare.util.Util;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class ZoomImageAdapter extends RecyclerView.Adapter<ZoomImageAdapter.MyViewHolder> {

    private List<DayCareBannerList> detailList;
    private Context context;
    private ViewGroup group;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price,name,type,city,university,comment,date,like,comment_count;
        public ImageView imageView,user_image,post_image;
        public PhotoView zoomView;
        public LinearLayout postLayout;

        public MyViewHolder(View view) {
            super(view);

            zoomView = (PhotoView)view.findViewById(R.id.photo_view);

        }
    }

    public ZoomImageAdapter(Context context, List<DayCareBannerList> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public ZoomImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.zoom_image_row, parent, false);
        group = parent;

        return new ZoomImageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ZoomImageAdapter.MyViewHolder holder, int position) {
        DayCareBannerList slider = detailList.get(position);

        Util.loadImage(holder.zoomView,slider.getBanner_url(),
                Util.getCircularDrawable(context));

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }
}
