package com.development.daycare.model.addBanner;

import java.util.List;

public class BannerListResponse {
    private int status;
    private List<BannerResponseListData> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<BannerResponseListData> getData() {
        return data;
    }

    public void setData(List<BannerResponseListData> data) {
        this.data = data;
    }
}
