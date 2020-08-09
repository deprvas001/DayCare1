package com.development.daycare.model.addDay;

import android.os.Parcel;
import android.os.Parcelable;

public class InHouseActivity implements Parcelable {
    private String id;
    private String daycare_id;
    private String activity_name;
    private String activity_image;
    private String activity_description;
    private String status;

    protected InHouseActivity(Parcel in) {
        id = in.readString();
        daycare_id = in.readString();
        activity_name = in.readString();
        activity_image = in.readString();
        activity_description = in.readString();
        status = in.readString();
    }

    public static final Creator<InHouseActivity> CREATOR = new Creator<InHouseActivity>() {
        @Override
        public InHouseActivity createFromParcel(Parcel in) {
            return new InHouseActivity(in);
        }

        @Override
        public InHouseActivity[] newArray(int size) {
            return new InHouseActivity[size];
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

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_image() {
        return activity_image;
    }

    public void setActivity_image(String activity_image) {
        this.activity_image = activity_image;
    }

    public String getActivity_description() {
        return activity_description;
    }

    public void setActivity_description(String activity_description) {
        this.activity_description = activity_description;
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
        parcel.writeString(activity_name);
        parcel.writeString(activity_image);
        parcel.writeString(activity_description);
        parcel.writeString(status);
    }
}
