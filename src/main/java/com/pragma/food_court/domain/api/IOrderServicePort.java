package com.pragma.food_court.domain.api;


import com.pragma.food_court.domain.model.Order;

public interface IOrderServicePort {
    void saveOrder(Order order);
}
