package com.pragma.food_court.adapters.driving.http.mapper;

import com.pragma.food_court.adapters.driving.http.dto.request.DishRequestDTO;
import com.pragma.food_court.adapters.driving.http.dto.response.DishResponse;
import com.pragma.food_court.domain.model.Dish;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface IDishRequestMapper {
    Dish toDomain(DishRequestDTO dishRequest);
    DishResponse toDTO(Dish dish);

    List<DishResponse> toDTOList(List<Dish> dishes);
}
