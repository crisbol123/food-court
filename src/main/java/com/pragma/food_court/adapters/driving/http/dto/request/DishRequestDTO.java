package com.pragma.food_court.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DishRequestDTO {
    private String name;
    private int price;
    private String description;
    private String imageUrl;
    private String category;
    private Long restaurantId;
}
