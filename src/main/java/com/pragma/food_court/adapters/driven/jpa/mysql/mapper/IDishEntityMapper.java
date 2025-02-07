package com.pragma.food_court.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.food_court.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface IDishEntityMapper {
     DishEntity toEntity(Dish dish);
    @Mapping(source = "restaurant.id", target = "restaurantId")
    Dish toDomain(DishEntity dishEntity);
}
