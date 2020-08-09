package com.development.daycare.model.addDay;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DayCareInfo implements Parcelable {
    private String id;
    private String daycare_id;
    private String contact_label;
    private String contact_name;
    private String contact_email;
    private String contact_phone;
    private String contact_alternate_phone;

    public DayCareInfo(){

    }

    protected DayCareInfo(Parcel in) {
        id = in.readString();
        daycare_id = in.readString();
        contact_label = in.readString();
        contact_name = in.readString();
        contact_email = in.readString();
        contact_phone = in.readString();
        contact_alternate_phone = in.readString();
    }

    public static final Creator<DayCareInfo> CREATOR = new Creator<DayCareInfo>() {
        @Override
        public DayCareInfo createFromParcel(Parcel in) {
            return new DayCareInfo(in);
        }

        @Override
        public DayCareInfo[] newArray(int size) {
            return new DayCareInfo[size];
        }
    };

    public String getContact_label() {
        return contact_label;
    }

    public void setContact_label(String contact_label) {
        this.contact_label = contact_label;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_alternate_phone() {
        return contact_alternate_phone;
    }

    public void setContact_alternate_phone(String contact_alternate_phone) {
        this.contact_alternate_phone = contact_alternate_phone;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(daycare_id);
        parcel.writeString(contact_label);
        parcel.writeString(contact_name);
        parcel.writeString(contact_email);
        parcel.writeString(contact_phone);
        parcel.writeString(contact_alternate_phone);
    }
}
