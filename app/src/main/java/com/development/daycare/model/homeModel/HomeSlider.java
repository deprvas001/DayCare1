package com.development.daycare.model.homeModel;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeSlider implements Parcelable {
    private String id;
    private String title;
    private String short_description;
    private String image;
    private String long_description;
    private String start_date;
    private String end_date;
    private String url;

    protected HomeSlider(Parcel in) {
        id = in.readString();
        title = in.readString();
        short_description = in.readString();
        image = in.readString();
        long_description = in.readString();
        start_date = in.readString();
        end_date = in.readString();
        url = in.readString();
    }

    public static final Creator<HomeSlider> CREATOR = new Creator<HomeSlider>() {
        @Override
        public HomeSlider createFromParcel(Parcel in) {
            return new HomeSlider(in);
        }

        @Override
        public HomeSlider[] newArray(int size) {
            return new HomeSlider[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(short_description);
        parcel.writeString(image);
        parcel.writeString(long_description);
        parcel.writeString(start_date);
        parcel.writeString(end_date);
        parcel.writeString(url);
    }
}
