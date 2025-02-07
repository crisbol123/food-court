package com.pragma.food_court.adapters.driven.jpa.mysql.mapper;


import com.pragma.food_court.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_court.domain.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurant);
     Restaurant toDomain(RestaurantEntity entity);

}