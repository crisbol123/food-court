package com.pragma.food_court.domain.model;

import java.util.List;

public class Order {
    private long restaurantId;
    private List<DishOrder> dishes;
    private long clientId;
    private long employeeId;
    private String state;

    public Order(long restaurantId, List<DishOrder> dishes, long clientId, long employeeId, String state) {
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<DishOrder> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishOrder> dishes) {
        this.dishes = dishes;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
}
