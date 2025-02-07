package com.pragma.food_court.domain.spi;

import com.pragma.food_court.domain.model.Dish;

import java.util.Optional;

public interface IDishPersistencePort {

    void createDish(Dish dish);

    void updateDish(Dish dish);

    Optional<Dish> findById(Long id);
}
