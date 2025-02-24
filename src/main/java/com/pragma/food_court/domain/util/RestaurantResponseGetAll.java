package com.pragma.food_court.domain.util;

public class RestaurantResponseGetAll {
    private String name;
    private String urlLogo;

    public RestaurantResponseGetAll(String name, String urlLogo) {
        this.name = name;
        this.urlLogo = urlLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
