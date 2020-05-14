package com.development.daycare.model.addCareActivity;

public class AddActivityApiResponse {
    public AddActivityResponse response;
    private Throwable error;
    private String message;
    private int status;
    private int status_code;

    public  AddActivityApiResponse(String message, int status, int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public AddActivityApiResponse(AddActivityResponse response) {
        this.response = response;
    }

    public AddActivityApiResponse(Throwable error) {
        this.error = error;
    }

    public AddActivityApiResponse(String message) {
        this.message = message;
    }

    public AddActivityApiResponse(int status) {
        this.status = status;
    }


    public AddActivityResponse getResponse() {
        return response;
    }

    public void setResponse(AddActivityResponse response) {
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
