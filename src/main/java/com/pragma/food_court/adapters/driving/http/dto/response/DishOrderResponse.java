package com.pragma.food_court.adapters.driving.http.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DishOrderResponse {
    private Long dishId;
    private int quantity;

}
