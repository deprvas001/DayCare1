package com.development.daycare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.development.daycare.R;
import com.development.daycare.model.BookmarkData;
import com.development.daycare.views.activity.DayCareDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.MyViewHolder> {
    private List<BookmarkData> bookmarkList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,age,resident,city,university,address;
        public ImageView roomate_image,fav_icon;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView)view.findViewById(R.id.cardView);
            name = (TextView)view.findViewById(R.id.name);
            age = (TextView)view.findViewById(R.id.age);
            resident = (TextView)view.findViewById(R.id.resident);
            city = (TextView)view.findViewById(R.id.host_city);
            university = (TextView)view.findViewById(R.id.university);
            roomate_image = (ImageView)view.findViewById(R.id.imageView);
            address = (TextView)view.findViewById(R.id.address);
            fav_icon = (ImageView)view.findViewById(R.id.favourite_icon);

        }
    }


    public BookmarkAdapter(Context context,List<BookmarkData> bookmarkList) {
        this.context = context;
        this.bookmarkList = bookmarkList;
    }

    @Override
    public BookmarkAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmark_row, parent, false);

        return new BookmarkAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookmarkAdapter.MyViewHolder holder, int position) {
        BookmarkData bookmark = bookmarkList.get(position);
        holder.name.setText(bookmark.getName());
        holder.age.setText(bookmark.getAge());
        holder.address.setText(bookmark.getAddress());
        holder.resident.setText(bookmark.getCountry());
        /*holder.city.setText(bookmark.getCity_name());
        holder.university.setText(bookmark.getUniversity_name());*/

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DayCareDetail.class);
                context.startActivity(intent);
            }
        });

        Picasso.get()
                .load(bookmark.getImage_url())
                // .placeholder(R.drawable.image1)
                //   .error(R.drawable.err)
                .into(holder.roomate_image);

    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

}
