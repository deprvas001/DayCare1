package com.development.daycare.model.dayCareList;

import com.development.daycare.model.homeModel.HomeResponse;

public class CareListApiResponse {

    public CareListResponse response;
    private Throwable error;
    private String message;
    private int status;


    public CareListResponse getResponse() {
        return response;
    }

    public void setResponse(CareListResponse response) {
        this.response = response;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
