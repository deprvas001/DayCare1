package com.development.daycare.model.loginModel;

public class LoginData {
    private String user_name;
    private String user_email;
    private String user_type;
    private String user_phone;
    private String user_id;
    private String AuthenticateToken;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAuthenticateToken() {
        return AuthenticateToken;
    }

    public void setAuthenticateToken(String authenticateToken) {
        AuthenticateToken = authenticateToken;
    }
}
