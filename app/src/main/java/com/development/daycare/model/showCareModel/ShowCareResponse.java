package com.development.daycare.model.showCareModel;

import java.util.List;

public class ShowCareResponse {
 private int status;
 private List<ShowCareData> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ShowCareData> getData() {
        return data;
    }

    public void setData(List<ShowCareData> data) {
        this.data = data;
    }
}
