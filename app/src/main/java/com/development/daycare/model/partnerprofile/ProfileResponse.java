package com.development.daycare.model.partnerprofile;

import java.util.List;

public class ProfileResponse {
    private int status;
    private String message;
    private ProfileData data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ProfileData getData() {
        return data;
    }

    public void setData(ProfileData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
