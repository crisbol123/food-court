package com.pragma.food_court.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.IDishEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.IDishRepository;
import com.pragma.food_court.domain.model.Dish;
import com.pragma.food_court.domain.spi.IDishPersistencePort;
import com.pragma.food_court.domain.spi.IRestaurantPersistencePort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DishAdapter implements IDishPersistencePort {
private final IRestaurantPersistencePort restaurantPersistencePort;
private final IDishEntityMapper dishEntityMapper;
private final IDishRepository dishRepository;
    @Override
    public void saveDish(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        dishEntity.setRestaurant(restaurantPersistencePort.findById(dish.getRestaurantId()));
        dishRepository.save(dishEntity);
    }


    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id).map(dishEntityMapper::toDomain);
    }


}
