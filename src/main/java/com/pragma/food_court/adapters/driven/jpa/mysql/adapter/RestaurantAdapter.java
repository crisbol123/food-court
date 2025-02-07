package com.pragma.food_court.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.pragma.food_court.domain.model.Restaurant;
import com.pragma.food_court.domain.spi.IRestaurantPersistencePort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RestaurantAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper  restaurantEntityMapper;
    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public boolean existsById(Long id) {

        return  restaurantRepository.existsById(id);
    }

    @Override
    public RestaurantEntity findById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
}

    @Override
    public Optional<Long> findOwnerIdByRestaurantId(Long id) {
        return restaurantRepository.findOwnerIdById(id);
    }
}
