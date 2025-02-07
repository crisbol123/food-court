package com.pragma.food_court.domain.api;

import com.pragma.food_court.domain.model.Dish;

public interface IDishServicePort {
    void createDish(Dish dish);
 void updateDish(Dish dish);
}
