package com.pragma.food_court.adapters.driving.http.dto.response;

import com.pragma.food_court.domain.model.DishOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponse {
    private long id;
    private long restaurantId;
    private List<DishOrder> dishes;
    private long clientId;
    private long employeeId;
    private String state;
}
