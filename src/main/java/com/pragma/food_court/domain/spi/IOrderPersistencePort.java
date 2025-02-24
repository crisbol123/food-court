package com.pragma.food_court.domain.spi;

import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.util.PagedResponse;

import java.util.List;
import java.util.Map;

public interface IOrderPersistencePort {
    long saveOrder(Order order);
    boolean hasOrder(long clientId);
    PagedResponse<Order> getAllOrders(int page, int size, String state, Long restaurantId);

    void assignOrder(long orderId, long employeeId, String state);
    void changeState(long orderId, String state);
Order getOrder(long orderId);
    List<Long> getOrdersIdByClientId(long clientId);
    List<Long> getAllOrdersId();
    Map<Long, Long>getOrdersIdAndEmployeeId();
}
