package com.pragma.food_court.domain.spi;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_court.domain.model.Restaurant;

import java.util.Optional;

public interface IRestaurantPersistencePort {

    void saveRestaurant(Restaurant restaurant);

    boolean existsById(Long id);
    RestaurantEntity findById(Long id);
    Optional<Long> findOwnerIdByRestaurantId(Long id);
}
