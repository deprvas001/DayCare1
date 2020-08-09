package com.development.daycare.model.addDay;

import android.os.Parcel;
import android.os.Parcelable;

public class DayCareBannerList implements Parcelable {
    private String id;
    private String daycare_id;
    private String banner_url;
    private String banner_label;
    private String status;


    protected DayCareBannerList(Parcel in) {
        id = in.readString();
        daycare_id = in.readString();
        banner_url = in.readString();
        banner_label = in.readString();
        status = in.readString();
    }

    public static final Creator<DayCareBannerList> CREATOR = new Creator<DayCareBannerList>() {
        @Override
        public DayCareBannerList createFromParcel(Parcel in) {
            return new DayCareBannerList(in);
        }

        @Override
        public DayCareBannerList[] newArray(int size) {
            return new DayCareBannerList[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDaycare_id() {
        return daycare_id;
    }

    public void setDaycare_id(String daycare_id) {
        this.daycare_id = daycare_id;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getBanner_label() {
        return banner_label;
    }

    public void setBanner_label(String banner_label) {
        this.banner_label = banner_label;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(daycare_id);
        parcel.writeString(banner_url);
        parcel.writeString(banner_label);
        parcel.writeString(status);
    }
}
