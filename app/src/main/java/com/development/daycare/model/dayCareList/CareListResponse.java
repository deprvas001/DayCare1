package com.development.daycare.model.dayCareList;

import java.util.List;

public class CareListResponse {
    private int status;
    private List<DayCareListModel> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DayCareListModel> getData() {
        return data;
    }

    public void setData(List<DayCareListModel> data) {
        this.data = data;
    }
}
