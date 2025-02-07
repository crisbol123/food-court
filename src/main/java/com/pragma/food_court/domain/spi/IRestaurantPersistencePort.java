package com.pragma.food_court.domain.spi;

import com.pragma.food_court.domain.model.Restaurant;

public interface IRestaurantPersistencePort {

    void saveRestaurant(Restaurant restaurant);
}
