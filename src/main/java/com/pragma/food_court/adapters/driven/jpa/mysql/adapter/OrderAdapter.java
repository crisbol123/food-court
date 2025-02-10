package com.pragma.food_court.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.DishOrderEntity;
import com.pragma.food_court.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.OrderEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.OrderRepository;
import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.spi.IOrderPersistencePort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;
@AllArgsConstructor
public class OrderAdapter implements IOrderPersistencePort {
    private final OrderRepository orderRepository;
  private final OrderEntityMapper orderEntityMapper;
    @Override
    public long saveOrder(Order order) {
      OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        for (DishOrderEntity dishOrder : orderEntity.getDishes()) {
            dishOrder.setOrder(orderEntity);
        }
       return orderRepository.save(orderEntity).getId();

    }

    @Override
    public boolean hasOrder(long clientId) {
        return orderRepository.existsByClientIdAndReadyFalse(clientId);
    }
}
