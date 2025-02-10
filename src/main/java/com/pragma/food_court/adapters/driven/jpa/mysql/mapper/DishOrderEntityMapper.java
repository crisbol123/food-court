package com.pragma.food_court.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.DishOrderEntity;
import com.pragma.food_court.domain.model.DishOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishOrderEntityMapper {
    DishOrderEntity toEntity(DishOrder dishOrder);
}
