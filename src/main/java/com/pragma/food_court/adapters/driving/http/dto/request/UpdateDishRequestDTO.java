package com.pragma.food_court.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UpdateDishRequestDTO {
    private int price;
    private String description;

}
