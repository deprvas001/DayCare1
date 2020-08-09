package com.development.daycare.model.showCareModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.development.daycare.model.addDay.CareOpenTime;
import com.development.daycare.model.addDay.DayCareBannerList;
import com.development.daycare.model.addDay.DayCareInfo;
import com.development.daycare.model.addDay.DayCareMenu;
import com.development.daycare.model.addDay.InHouseActivity;
import com.development.daycare.model.addDay.SubjectList;
import com.development.daycare.views.activity.dayCareBanner.DayCareBanner;

import java.util.List;

public class ShowCareData implements Parcelable {
    private String daycare_id;
    private String daycare_name;
    private String re_name_slug_url;
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
    private String daycare_rating_value;
    private String daycare_cctv_value;
    private String daycare_type_of_meals;
    private String daycare_transportation_required;
    private String daycare_type;
    private String daycare_status;
    private List<DayCareInfo>  daycare_contact_info;
    private List<DayCareMenu> daycare_menu_list;
    private List<CareOpenTime> daycare_open_hours;
    private List<SubjectList> daycare_tution_subject_list;
    private List<DayCareBannerList> daycare_banner_list;
    private List<InHouseActivity> daycare_inhouse_activity_list;
    private String daycare_distance;

    protected ShowCareData(Parcel in) {
        daycare_id = in.readString();
        daycare_name = in.readString();
        re_name_slug_url = in.readString();
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
        daycare_rating_value = in.readString();
        daycare_cctv_value = in.readString();
        daycare_type_of_meals = in.readString();
        daycare_transportation_required = in.readString();
        daycare_type = in.readString();
        daycare_status = in.readString();
        daycare_contact_info = in.createTypedArrayList(DayCareInfo.CREATOR);
        daycare_menu_list = in.createTypedArrayList(DayCareMenu.CREATOR);
        daycare_open_hours = in.createTypedArrayList(CareOpenTime.CREATOR);
        daycare_tution_subject_list = in.createTypedArrayList(SubjectList.CREATOR);
        daycare_banner_list = in.createTypedArrayList(DayCareBannerList.CREATOR);
        daycare_inhouse_activity_list = in.createTypedArrayList(InHouseActivity.CREATOR);
        daycare_distance = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(daycare_id);
        dest.writeString(daycare_name);
        dest.writeString(re_name_slug_url);
        dest.writeString(daycare_address);
        dest.writeString(daycare_latitude);
        dest.writeString(daycare_longitude);
        dest.writeString(daycare_logo_url);
        dest.writeString(daycare_short_description);
        dest.writeString(daycare_long_description);
        dest.writeString(daycare_child_age_min_value);
        dest.writeString(daycare_child_age_max_value);
        dest.writeString(daycare_budget_min_value);
        dest.writeString(daycare_budget_max_value);
        dest.writeString(daycare_rating_value);
        dest.writeString(daycare_cctv_value);
        dest.writeString(daycare_type_of_meals);
        dest.writeString(daycare_transportation_required);
        dest.writeString(daycare_type);
        dest.writeString(daycare_status);
        dest.writeTypedList(daycare_contact_info);
        dest.writeTypedList(daycare_menu_list);
        dest.writeTypedList(daycare_open_hours);
        dest.writeTypedList(daycare_tution_subject_list);
        dest.writeTypedList(daycare_banner_list);
        dest.writeTypedList(daycare_inhouse_activity_list);
        dest.writeString(daycare_distance);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShowCareData> CREATOR = new Creator<ShowCareData>() {
        @Override
        public ShowCareData createFromParcel(Parcel in) {
            return new ShowCareData(in);
        }

        @Override
        public ShowCareData[] newArray(int size) {
            return new ShowCareData[size];
        }
    };

    public String getDaycare_id() {
        return daycare_id;
    }

    public void setDaycare_id(String daycare_id) {
        this.daycare_id = daycare_id;
    }

    public String getDaycare_name() {
        return daycare_name;
    }

    public void setDaycare_name(String daycare_name) {
        this.daycare_name = daycare_name;
    }

    public String getRe_name_slug_url() {
        return re_name_slug_url;
    }

    public void setRe_name_slug_url(String re_name_slug_url) {
        this.re_name_slug_url = re_name_slug_url;
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

    public String getDaycare_rating_value() {
        return daycare_rating_value;
    }

    public void setDaycare_rating_value(String daycare_rating_value) {
        this.daycare_rating_value = daycare_rating_value;
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

    public String getDaycare_transportation_required() {
        return daycare_transportation_required;
    }

    public void setDaycare_transportation_required(String daycare_transportation_required) {
        this.daycare_transportation_required = daycare_transportation_required;
    }

    public String getDaycare_type() {
        return daycare_type;
    }

    public void setDaycare_type(String daycare_type) {
        this.daycare_type = daycare_type;
    }

    public String getDaycare_status() {
        return daycare_status;
    }

    public void setDaycare_status(String daycare_status) {
        this.daycare_status = daycare_status;
    }

    public List<DayCareInfo> getDaycare_contact_info() {
        return daycare_contact_info;
    }

    public void setDaycare_contact_info(List<DayCareInfo> daycare_contact_info) {
        this.daycare_contact_info = daycare_contact_info;
    }

    public List<DayCareMenu> getDaycare_menu_list() {
        return daycare_menu_list;
    }

    public void setDaycare_menu_list(List<DayCareMenu> daycare_menu_list) {
        this.daycare_menu_list = daycare_menu_list;
    }

    public List<CareOpenTime> getDaycare_open_hours() {
        return daycare_open_hours;
    }

    public void setDaycare_open_hours(List<CareOpenTime> daycare_open_hours) {
        this.daycare_open_hours = daycare_open_hours;
    }

    public List<SubjectList> getDaycare_tution_subject_list() {
        return daycare_tution_subject_list;
    }

    public void setDaycare_tution_subject_list(List<SubjectList> daycare_tution_subject_list) {
        this.daycare_tution_subject_list = daycare_tution_subject_list;
    }

    public List<DayCareBannerList> getDaycare_banner_list() {
        return daycare_banner_list;
    }

    public void setDaycare_banner_list(List<DayCareBannerList> daycare_banner_list) {
        this.daycare_banner_list = daycare_banner_list;
    }

    public List<InHouseActivity> getDaycare_inhouse_activity_list() {
        return daycare_inhouse_activity_list;
    }

    public void setDaycare_inhouse_activity_list(List<InHouseActivity> daycare_inhouse_activity_list) {
        this.daycare_inhouse_activity_list = daycare_inhouse_activity_list;
    }

    public String getDaycare_distance() {
        return daycare_distance;
    }

    public void setDaycare_distance(String daycare_distance) {
        this.daycare_distance = daycare_distance;
    }
}
