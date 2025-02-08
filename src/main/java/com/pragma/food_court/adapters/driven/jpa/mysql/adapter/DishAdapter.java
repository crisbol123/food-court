package com.pragma.food_court.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.IDishEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.IDishRepository;
import com.pragma.food_court.domain.model.Dish;
import com.pragma.food_court.domain.spi.IDishPersistencePort;
import com.pragma.food_court.domain.spi.IRestaurantPersistencePort;
import com.pragma.food_court.domain.util.PagedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
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

    @Override
    public PagedResponse<Dish> getAllDishes(int page, int size, String category, long restaurantId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DishEntity> dishEntities = dishRepository.findByCategoryAndRestaurantId(category, restaurantId, pageable);
        List<Dish> dishes = dishEntityMapper.toDomainList(dishEntities.getContent());

        return new PagedResponse<>(dishes, page, dishEntities.getTotalPages(), dishEntities.getTotalElements(), dishEntities.isLast() );
} }
