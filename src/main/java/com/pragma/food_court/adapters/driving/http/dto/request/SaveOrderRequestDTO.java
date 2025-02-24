package com.pragma.food_court.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class SaveOrderRequestDTO {
    private long restaurantId;
    private List<DishOrderDTO> dishes;
}
