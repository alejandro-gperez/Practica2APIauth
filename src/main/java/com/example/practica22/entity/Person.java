package com.example.practica22.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

    private static final long serialVersionUID = -6980135520L;

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_key_creation")
    private LocalDateTime apiKeyCreation;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public LocalDateTime getApiKeyCreation() {
        return this.apiKeyCreation;
    }

    public void setApiKeyCreation(LocalDateTime apiKeyCreation) {
        this.apiKeyCreation = apiKeyCreation;
    }
}
