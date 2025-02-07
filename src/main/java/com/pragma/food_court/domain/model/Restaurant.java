package com.pragma.food_court.domain.model;


import com.pragma.food_court.domain.exception.InvalidNameException;
import com.pragma.food_court.domain.exception.InvalidNitException;
import com.pragma.food_court.domain.exception.InvalidPhoneException;

public class Restaurant {
 private String name;
 private String nit;
    private String address;
    private String phone;
    private String logoUrl;
    private Long ownerId;

    public Restaurant(String name, String nit, String address, String phone, String logoUrl, Long ownerId) {
        if (!name.matches(".*\\D.*")) {
            throw new InvalidNameException("Name cannot be only numeric");
        }
        if (!nit.matches("\\d+")) {
            throw new InvalidNitException("NIT must be numeric");
        }
        if (!phone.matches("\\+?\\d+") || phone.length() > 13) {
            throw new InvalidPhoneException("Phone must be numeric, can contain +, and must be at most 13 characters");
        }

        this.name = name;
        this.nit = nit;
        this.address = address;
        this.phone = phone;
        this.logoUrl = logoUrl;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}