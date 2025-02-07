package com.pragma.food_court.domain.spi;

import com.pragma.food_court.domain.model.Dish;

import java.util.Optional;

public interface IDishPersistencePort {

    void saveDish(Dish dish);


    Optional<Dish> findById(Long id);
}
