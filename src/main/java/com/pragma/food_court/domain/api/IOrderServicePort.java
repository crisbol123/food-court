package com.pragma.food_court.domain.api;


import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.util.PagedResponse;

import java.util.List;
import java.util.Map;

public interface IOrderServicePort {
    void saveOrder(Order order);

    PagedResponse<Order> getAllOrders(int page, int size, String status);
void assignOrder(long orderId);
void notifyOrderReady(long orderId);
void deliverOrder(long orderId,String securityCode);
void cancelOrder(long orderId);
List<Long> getOrdersIdByClientId(long clientId);
List<Long> getAllOrdersId();
Map<Long, Long> getOrdersIdAndEmployeeId();
}
