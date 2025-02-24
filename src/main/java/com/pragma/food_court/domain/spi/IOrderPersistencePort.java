package com.pragma.food_court.domain.spi;

import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.util.PagedResponse;

public interface IOrderPersistencePort {
    long saveOrder(Order order);
    boolean hasOrder(long clientId);
    PagedResponse<Order> getAllOrdersByClientId( int page, int size, Long restaurantId);
void updateState(Order order);

Order getOrder(long orderId);
}
