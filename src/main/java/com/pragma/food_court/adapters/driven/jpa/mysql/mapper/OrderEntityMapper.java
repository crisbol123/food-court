package com.pragma.food_court.adapters.driven.jpa.mysql.mapper;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.food_court.domain.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DishOrderEntityMapper.class})
public interface OrderEntityMapper {
    OrderEntity toEntity(Order order);

    Order toDomain(OrderEntity orderEntity);



}
