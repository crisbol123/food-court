package com.pragma.food_court.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDTO {
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String logoUrl;
    private Long ownerId;

}
