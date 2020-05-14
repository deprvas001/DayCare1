package com.development.daycare.model.addCareActivity;

public class ActivityListApiResponse {

    public ActivityListResponse response;
    private Throwable error;
    private String message;
    private int status;
    private int status_code;

    public  ActivityListApiResponse(String message, int status, int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public ActivityListApiResponse(ActivityListResponse response) {
        this.response = response;
    }

    public ActivityListApiResponse(Throwable error) {
        this.error = error;
    }

    public ActivityListResponse getResponse() {
        return response;
    }

    public void setResponse(ActivityListResponse response) {
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
