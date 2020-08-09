package com.development.daycare.model.dayCareList;

public class DayCareListPost {
    private String offset;
    private String per_page;
    private String startlat;
    private String startlng;
    private String distance;
    private String searchvalue;

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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSearchvalue() {
        return searchvalue;
    }

    public void setSearchvalue(String searchvalue) {
        this.searchvalue = searchvalue;
    }
}
