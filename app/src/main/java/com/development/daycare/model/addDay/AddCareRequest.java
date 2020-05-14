package com.development.daycare.model.addDay;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddCareRequest implements Parcelable {
    private String daycare_name;
    private String daycare_address;
    private String daycare_latitude;
    private String daycare_longitude;
    private String daycare_logo_url;
    private String daycare_short_description;
    private String daycare_long_description;
    private String daycare_child_age_min_value;
    private String daycare_child_age_max_value;
    private String daycare_budget_min_value;
    private String daycare_budget_max_value;
    private String daycare_cctv_value;
    private String daycare_type_of_meals;
    @SerializedName("daycare_meals_menu_list")
    private List<DayCareMenu> meal_menu_list;
    @SerializedName("daycare_transportation_required")
    private String transport_required;
    private String daycare_type;
    @SerializedName("daycare_contact_info")
    private List<DayCareInfo> contact_info;
    @SerializedName("daycare_open_hours")
    private List<CareOpenTime> opening_time;
    @SerializedName("daycare_tution_subject_list")
    private List<SubjectList> subject_list;

    public AddCareRequest(){

    }


    protected AddCareRequest(Parcel in) {
        daycare_name = in.readString();
        daycare_address = in.readString();
        daycare_latitude = in.readString();
        daycare_longitude = in.readString();
        daycare_logo_url = in.readString();
        daycare_short_description = in.readString();
        daycare_long_description = in.readString();
        daycare_child_age_min_value = in.readString();
        daycare_child_age_max_value = in.readString();
        daycare_budget_min_value = in.readString();
        daycare_budget_max_value = in.readString();
        daycare_cctv_value = in.readString();
        daycare_type_of_meals = in.readString();
        meal_menu_list = in.createTypedArrayList(DayCareMenu.CREATOR);
        transport_required = in.readString();
        daycare_type = in.readString();
        contact_info = in.createTypedArrayList(DayCareInfo.CREATOR);
        opening_time = in.createTypedArrayList(CareOpenTime.CREATOR);
        subject_list = in.createTypedArrayList(SubjectList.CREATOR);
    }

    public static final Creator<AddCareRequest> CREATOR = new Creator<AddCareRequest>() {
        @Override
        public AddCareRequest createFromParcel(Parcel in) {
            return new AddCareRequest(in);
        }

        @Override
        public AddCareRequest[] newArray(int size) {
            return new AddCareRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getDaycare_name() {
        return daycare_name;
    }

    public void setDaycare_name(String daycare_name) {
        this.daycare_name = daycare_name;
    }

    public String getDaycare_address() {
        return daycare_address;
    }

    public void setDaycare_address(String daycare_address) {
        this.daycare_address = daycare_address;
    }

    public String getDaycare_latitude() {
        return daycare_latitude;
    }

    public void setDaycare_latitude(String daycare_latitude) {
        this.daycare_latitude = daycare_latitude;
    }

    public String getDaycare_longitude() {
        return daycare_longitude;
    }

    public void setDaycare_longitude(String daycare_longitude) {
        this.daycare_longitude = daycare_longitude;
    }

    public String getDaycare_logo_url() {
        return daycare_logo_url;
    }

    public void setDaycare_logo_url(String daycare_logo_url) {
        this.daycare_logo_url = daycare_logo_url;
    }

    public String getDaycare_short_description() {
        return daycare_short_description;
    }

    public void setDaycare_short_description(String daycare_short_description) {
        this.daycare_short_description = daycare_short_description;
    }

    public String getDaycare_long_description() {
        return daycare_long_description;
    }

    public void setDaycare_long_description(String daycare_long_description) {
        this.daycare_long_description = daycare_long_description;
    }

    public String getDaycare_child_age_min_value() {
        return daycare_child_age_min_value;
    }

    public void setDaycare_child_age_min_value(String daycare_child_age_min_value) {
        this.daycare_child_age_min_value = daycare_child_age_min_value;
    }

    public String getDaycare_child_age_max_value() {
        return daycare_child_age_max_value;
    }

    public void setDaycare_child_age_max_value(String daycare_child_age_max_value) {
        this.daycare_child_age_max_value = daycare_child_age_max_value;
    }

    public String getDaycare_budget_min_value() {
        return daycare_budget_min_value;
    }

    public void setDaycare_budget_min_value(String daycare_budget_min_value) {
        this.daycare_budget_min_value = daycare_budget_min_value;
    }

    public String getDaycare_budget_max_value() {
        return daycare_budget_max_value;
    }

    public void setDaycare_budget_max_value(String daycare_budget_max_value) {
        this.daycare_budget_max_value = daycare_budget_max_value;
    }

    public String getDaycare_cctv_value() {
        return daycare_cctv_value;
    }

    public void setDaycare_cctv_value(String daycare_cctv_value) {
        this.daycare_cctv_value = daycare_cctv_value;
    }

    public String getDaycare_type_of_meals() {
        return daycare_type_of_meals;
    }

    public void setDaycare_type_of_meals(String daycare_type_of_meals) {
        this.daycare_type_of_meals = daycare_type_of_meals;
    }

    public List<DayCareMenu> getMeal_menu_list() {
        return meal_menu_list;
    }

    public void setMeal_menu_list(List<DayCareMenu> meal_menu_list) {
        this.meal_menu_list = meal_menu_list;
    }

    public String getTransport_required() {
        return transport_required;
    }

    public void setTransport_required(String transport_required) {
        this.transport_required = transport_required;
    }

    public String getDaycare_type() {
        return daycare_type;
    }

    public void setDaycare_type(String daycare_type) {
        this.daycare_type = daycare_type;
    }

    public List<DayCareInfo> getContact_info() {
        return contact_info;
    }

    public void setContact_info(List<DayCareInfo> contact_info) {
        this.contact_info = contact_info;
    }

    public List<CareOpenTime> getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(List<CareOpenTime> opening_time) {
        this.opening_time = opening_time;
    }

    public List<SubjectList> getSubject_list() {
        return subject_list;
    }

    public void setSubject_list(List<SubjectList> subject_list) {
        this.subject_list = subject_list;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(daycare_name);
        parcel.writeString(daycare_address);
        parcel.writeString(daycare_latitude);
        parcel.writeString(daycare_longitude);
        parcel.writeString(daycare_logo_url);
        parcel.writeString(daycare_short_description);
        parcel.writeString(daycare_long_description);
        parcel.writeString(daycare_child_age_min_value);
        parcel.writeString(daycare_child_age_max_value);
        parcel.writeString(daycare_budget_min_value);
        parcel.writeString(daycare_budget_max_value);
        parcel.writeString(daycare_cctv_value);
        parcel.writeString(daycare_type_of_meals);
        parcel.writeTypedList(meal_menu_list);
        parcel.writeString(transport_required);
        parcel.writeString(daycare_type);
        parcel.writeTypedList(contact_info);
        parcel.writeTypedList(opening_time);
        parcel.writeTypedList(subject_list);
    }
}
