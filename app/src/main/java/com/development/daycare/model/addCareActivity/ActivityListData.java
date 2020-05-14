package com.development.daycare.model.addCareActivity;

public class ActivityListData {
    private String activity_id;
    private String daycare_id;
    private String  daycare_activity_image;
    private String daycare_activity_name;
    private String daycare_activity_description;
    private String activity_status;

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getDaycare_id() {
        return daycare_id;
    }

    public void setDaycare_id(String daycare_id) {
        this.daycare_id = daycare_id;
    }

    public String getDaycare_activity_image() {
        return daycare_activity_image;
    }

    public void setDaycare_activity_image(String daycare_activity_image) {
        this.daycare_activity_image = daycare_activity_image;
    }

    public String getDaycare_activity_name() {
        return daycare_activity_name;
    }

    public void setDaycare_activity_name(String daycare_activity_name) {
        this.daycare_activity_name = daycare_activity_name;
    }

    public String getDaycare_activity_description() {
        return daycare_activity_description;
    }

    public void setDaycare_activity_description(String daycare_activity_description) {
        this.daycare_activity_description = daycare_activity_description;
    }

    public String getActivity_status() {
        return activity_status;
    }

    public void setActivity_status(String activity_status) {
        this.activity_status = activity_status;
    }
}
