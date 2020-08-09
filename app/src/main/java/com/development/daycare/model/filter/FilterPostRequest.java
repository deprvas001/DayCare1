package com.development.daycare.model.filter;

public class FilterPostRequest {
    private String offset;
    private String per_page;
    private String startlat;
    private String startlng;
    private String price_min_value;
    private String price_max_value;
    private String age_min_value;
    private String age_max_value;
    private String daycare_type;
    private String daycare_cctv_value;
    private String daycare_type_of_meals;
    private String daycare_transportation_required;
    private String daycare_rating_value;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getStartlat() {
        return startlat;
    }

    public void setStartlat(String startlat) {
        this.startlat = startlat;
    }

    public String getStartlng() {
        return startlng;
    }

    public void setStartlng(String startlng) {
        this.startlng = startlng;
    }

    public String getPrice_min_value() {
        return price_min_value;
    }

    public void setPrice_min_value(String price_min_value) {
        this.price_min_value = price_min_value;
    }

    public String getPrice_max_value() {
        return price_max_value;
    }

    public void setPrice_max_value(String price_max_value) {
        this.price_max_value = price_max_value;
    }

    public String getAge_min_value() {
        return age_min_value;
    }

    public void setAge_min_value(String age_min_value) {
        this.age_min_value = age_min_value;
    }

    public String getAge_max_value() {
        return age_max_value;
    }

    public void setAge_max_value(String age_max_value) {
        this.age_max_value = age_max_value;
    }

    public String getDaycare_type() {
        return daycare_type;
    }

    public void setDaycare_type(String daycare_type) {
        this.daycare_type = daycare_type;
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

    public String getDaycare_rating_value() {
        return daycare_rating_value;
    }

    public void setDaycare_rating_value(String daycare_rating_value) {
        this.daycare_rating_value = daycare_rating_value;
    }
}
