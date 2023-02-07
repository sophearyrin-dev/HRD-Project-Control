package com.kshrd.hrdprojectcontrolapi.rest.request;

import java.io.Serializable;

public class LoginApiRequest implements Serializable {
    private static final long serialVersionUID = -5616176897013108345L;
    private String username;
    private String password;

    public LoginApiRequest() {
    }

    public LoginApiRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginApiRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
