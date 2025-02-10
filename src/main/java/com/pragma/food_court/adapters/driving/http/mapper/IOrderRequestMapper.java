package com.pragma.food_court.adapters.driving.http.mapper;


import com.pragma.food_court.adapters.driving.http.dto.request.SaveOrderRequestDTO;
import com.pragma.food_court.domain.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IDishOrderRequestMapper.class})

public interface IOrderRequestMapper {
    Order toDomain(SaveOrderRequestDTO request);
}
