package com.development.daycare.model.addCareActivity;

import java.util.List;

public class ActivityListResponse {
    private int status;
    private List<ActivityListData> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ActivityListData> getData() {
        return data;
    }

    public void setData(List<ActivityListData> data) {
        this.data = data;
    }
}
