package com.example.practica22.dto;

import java.io.Serializable;

public class LoginResponseDTO implements Serializable {

    private static final long serialVersionUID = -1366549997L;

    private String apiKey;

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
