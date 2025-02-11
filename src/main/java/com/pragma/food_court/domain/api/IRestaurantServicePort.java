package com.pragma.food_court.domain.api;


import com.pragma.food_court.domain.model.Restaurant;
import com.pragma.food_court.domain.util.PagedResponse;
import com.pragma.food_court.domain.util.RestaurantResponseGetAll;

public interface IRestaurantServicePort {
    void saveRestaurant( Restaurant restaurant);
    PagedResponse<RestaurantResponseGetAll> getAllRestaurants(int page, int size, boolean ascOrder);
    void createEmployee(long restaurantId, long employeeId);
    long getRestaurantIdByEmployeeId(long employeeId);
}
