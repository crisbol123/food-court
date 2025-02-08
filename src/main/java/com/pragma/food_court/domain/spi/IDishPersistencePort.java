package com.pragma.food_court.domain.spi;

import com.pragma.food_court.adapters.driving.http.dto.response.DishResponse;
import com.pragma.food_court.domain.model.Dish;
import com.pragma.food_court.domain.util.PagedResponse;

import java.util.Optional;

public interface IDishPersistencePort {

    void saveDish(Dish dish);


    Optional<Dish> findById(Long id);
    PagedResponse<Dish> getAllDishes(int page, int size, String category, long restaurantId);

}
