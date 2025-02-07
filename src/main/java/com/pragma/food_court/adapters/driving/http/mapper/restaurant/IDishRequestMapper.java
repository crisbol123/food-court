package com.pragma.food_court.adapters.driving.http.mapper.restaurant;

import com.pragma.food_court.adapters.driving.http.dto.request.DishRequestDTO;
import com.pragma.food_court.domain.model.Dish;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface IDishRequestMapper {
    Dish toDomain(DishRequestDTO dishRequest);
}
