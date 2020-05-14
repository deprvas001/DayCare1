package com.development.daycare.model.showCareModel;

public class ShowCareResponse {
 private int status;
 private ShowCareData data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ShowCareData getData() {
        return data;
    }

    public void setData(ShowCareData data) {
        this.data = data;
    }
}
