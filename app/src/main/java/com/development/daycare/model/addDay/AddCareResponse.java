package com.development.daycare.model.addDay;

import com.google.gson.annotations.SerializedName;

public class AddCareResponse {
    private int status;
    private String message;
    private DayCare data;

    public DayCare getData() {
        return data;
    }

    public void setData(DayCare data) {
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
