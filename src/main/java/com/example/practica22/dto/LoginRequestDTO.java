package com.example.practica22.dto;

import java.io.Serializable;

public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = -4011456970L;

    private String userName;
    private String password;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
