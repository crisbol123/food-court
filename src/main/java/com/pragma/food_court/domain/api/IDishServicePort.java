package com.pragma.food_court.domain.api;

import com.pragma.food_court.domain.model.Dish;
import com.pragma.food_court.domain.util.PagedResponse;

public interface IDishServicePort {
    void createDish(Dish dish);
 void updateDish(Dish dish);
 void enableDisableDish(Long id);
 PagedResponse<Dish> getAllDishes(int page, int size,String category, long restaurantId);
}
