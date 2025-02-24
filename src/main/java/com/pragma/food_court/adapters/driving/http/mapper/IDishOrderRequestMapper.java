package com.pragma.food_court.adapters.driving.http.mapper;

import com.pragma.food_court.adapters.driving.http.dto.request.DishOrderDTO;
import com.pragma.food_court.adapters.driving.http.dto.response.DishOrderResponse;
import com.pragma.food_court.domain.model.DishOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDishOrderRequestMapper {
    DishOrder toDomain(DishOrderDTO request);
    DishOrderResponse toResponse(DishOrder dishOrder);
}
