package com.pragma.food_court.adapters.driving.http.mapper;


import com.pragma.food_court.adapters.driving.http.dto.request.RestaurantRequestDTO;
import com.pragma.food_court.domain.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRestaurantRequestMapper {
    Restaurant toDomain(RestaurantRequestDTO request);

    }



