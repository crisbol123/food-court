package com.pragma.food_court.adapters.driven.feigns.dto;

public class AuthorizationRequest {
    private String token;


    public AuthorizationRequest(String token) {
        this.token = token;

    }

    public String getToken() {
        return token;
    }


}
