package com.pragma.food_court.domain.api;


import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.util.PagedResponse;

public interface IOrderServicePort {
    void saveOrder(Order order);

    PagedResponse<Order> getAllOrdersByClientId( int page, int size);
void assignOrder(long orderId);
void notifyOrderReady(long orderId);
}
