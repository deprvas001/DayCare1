package com.development.daycare.model.addDay;

import com.development.daycare.model.loginModel.LoginResponse;

public class AddCareApiResponse {
    public AddCareResponse response;
    private Throwable error;
    private String message;
    private int status;
    private int status_code;

    public AddCareApiResponse(String message, int status, int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public AddCareApiResponse(AddCareResponse response) {
        this.response = response;
    }

    public AddCareApiResponse(Throwable error) {
        this.error = error;
    }

    public AddCareApiResponse(String message) {
        this.message = message;
    }

    public AddCareApiResponse(int status) {
        this.status = status;
    }

    public AddCareResponse getResponse() {
        return response;
    }

    public void setResponse(AddCareResponse response) {
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
