package com.pragma.food_court.domain.spi;

import com.pragma.food_court.domain.model.Order;

public interface IOrderPersistencePort {
    long saveOrder(Order order);
    boolean hasOrder(long clientId);
}
