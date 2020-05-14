package com.development.daycare.model.partnerprofile;

import java.util.List;

public class ProfileResponse {
    private int status;
    private List<ProfileData> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ProfileData> getData() {
        return data;
    }

    public void setData(List<ProfileData> data) {
        this.data = data;
    }
}
