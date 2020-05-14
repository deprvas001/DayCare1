package com.development.daycare.model.addDay;

import android.os.Parcel;
import android.os.Parcelable;

public class CareOpenTime implements Parcelable {
    private String daycare_day_id;
    private String  daycare_open_time;
    private String  daycare_close_time;
    private String daycare_extra_info;

    public CareOpenTime(){

    }

    protected CareOpenTime(Parcel in) {
        daycare_day_id= in.readString();
        daycare_open_time = in.readString();
        daycare_close_time = in.readString();
        daycare_extra_info = in.readString();
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

    public String getDaycare_day_id() {
        return daycare_day_id;
    }

    public void setDaycare_day_id(String daycare_day_id) {
        this.daycare_day_id = daycare_day_id;
    }

    public String getDaycare_open_time() {
        return daycare_open_time;
    }

    public void setDaycare_open_time(String daycare_open_time) {
        this.daycare_open_time = daycare_open_time;
    }

    public String getDaycare_close_time() {
        return daycare_close_time;
    }

    public void setDaycare_close_time(String daycare_close_time) {
        this.daycare_close_time = daycare_close_time;
    }

    public String getDaycare_extra_info() {
        return daycare_extra_info;
    }

    public void setDaycare_extra_info(String daycare_extra_info) {
        this.daycare_extra_info = daycare_extra_info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(daycare_day_id);
        parcel.writeString(daycare_open_time);
        parcel.writeString(daycare_close_time);
        parcel.writeString(daycare_extra_info);
    }
}
