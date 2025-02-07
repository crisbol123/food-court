package com.pragma.food_court.domain.api;


import com.pragma.food_court.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void saveRestaurant( Restaurant restaurant);
}
