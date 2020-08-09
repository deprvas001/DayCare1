package com.development.daycare.model.addDay;

import android.os.Parcel;
import android.os.Parcelable;

public class CareOpenTime implements Parcelable {
   private String day_id;
   private String open_time;
   private String close_time;
   private String extra_info;

   public CareOpenTime(){

   }

    protected CareOpenTime(Parcel in) {
        day_id = in.readString();
        open_time = in.readString();
        close_time = in.readString();
        extra_info = in.readString();
    }

    public static final Creator<CareOpenTime> CREATOR = new Creator<CareOpenTime>() {
        @Override
        public CareOpenTime createFromParcel(Parcel in) {
            return new CareOpenTime(in);
        }

        @Override
        public CareOpenTime[] newArray(int size) {
            return new CareOpenTime[size];
        }
    };

    public String getDay_id() {
        return day_id;
    }

    public void setDay_id(String day_id) {
        this.day_id = day_id;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getExtra_info() {
        return extra_info;
    }

    public void setExtra_info(String extra_info) {
        this.extra_info = extra_info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(day_id);
        parcel.writeString(open_time);
        parcel.writeString(close_time);
        parcel.writeString(extra_info);
    }
}
