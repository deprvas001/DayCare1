package com.development.daycare.model.addBanner;

public class AddBannerRequest {
    private String daycare_id;
    private String daycare_banner_label;
    private String  daycare_banner_url;


    public String getDaycare_id() {
        return daycare_id;
    }

    public void setDaycare_id(String daycare_id) {
        this.daycare_id = daycare_id;
    }

    public String getDaycare_banner_label() {
        return daycare_banner_label;
    }

    public void setDaycare_banner_label(String daycare_banner_label) {
        this.daycare_banner_label = daycare_banner_label;
    }

    public String getDaycare_banner_url() {
        return daycare_banner_url;
    }

    public void setDaycare_banner_url(String daycare_banner_url) {
        this.daycare_banner_url = daycare_banner_url;
    }
}
