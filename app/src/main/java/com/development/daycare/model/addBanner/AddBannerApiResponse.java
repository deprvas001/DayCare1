package com.development.daycare.model.addBanner;

import com.development.daycare.model.addDay.AddCareResponse;

public class AddBannerApiResponse {

    public AddBannerResponse response;
    private Throwable error;
    private String message;
    private int status;
    private int status_code;

    public  AddBannerApiResponse(String message, int status, int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public AddBannerApiResponse(AddBannerResponse response) {
        this.response = response;
    }

    public AddBannerApiResponse(Throwable error) {
        this.error = error;
    }

    public AddBannerApiResponse(String message) {
        this.message = message;
    }

    public AddBannerResponse getResponse() {
        return response;
    }

    public void setResponse(AddBannerResponse response) {
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

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
}
