package com.development.daycare.model.addBanner;

import java.util.List;

public class AddBannerResponse {
    private int status;
    private String message;
    private BannerData data;

    public BannerData getData() {
        return data;
    }

    public void setData(BannerData data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
