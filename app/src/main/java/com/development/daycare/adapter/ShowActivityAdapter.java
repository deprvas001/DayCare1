package com.development.daycare.adapter;

import android.app.Dialog;
import android.content.Context;
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
import com.development.daycare.model.addDay.InHouseActivity;
import com.development.daycare.util.Util;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowActivityAdapter extends RecyclerView.Adapter<ShowActivityAdapter.MyViewHolder>  {

    private List<InHouseActivity> bannerList;
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


    public ShowActivityAdapter(Context context,List<InHouseActivity> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    @Override
    public ShowActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_list_row, parent, false);

        return new ShowActivityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowActivityAdapter.MyViewHolder holder, int position) {
        InHouseActivity listData = bannerList.get(position);
        holder.name.setText(listData.getActivity_name());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageDialog1(listData.getActivity_image());
            }
        });

        Util.loadImage(holder.logo,listData.getActivity_image() ,
                Util.getCircularDrawable(context));

    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    public void showImageDialog1(String image) {
        // custom dialog
        dialog = new Dialog( context);
        dialog.setContentView(R.layout.property_view);

        // set the custom dialog components - text, image and button
        ImageButton close = (ImageButton) dialog.findViewById(R.id.btnClose);
        PhotoView imageView = dialog.findViewById(R.id.photo_view);

       /* Picasso.get()
                .load(image)
                .placeholder(R.drawable.no_image_available)
                .error(R.drawable.no_image_available)
                .into(imageView);
*/
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
