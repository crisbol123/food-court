package com.pragma.food_court.adapters.driving.http.controller;

import com.pragma.food_court.adapters.driving.http.dto.request.RestaurantRequestDTO;
import com.pragma.food_court.adapters.driving.http.mapper.restaurant.IRestaurantRequestMapper;
import com.pragma.food_court.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {
private final IRestaurantServicePort restaurantServicePort;
private final IRestaurantRequestMapper restaurantRequestMapper;

    @PostMapping("/save")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequestDTO request) {
restaurantServicePort.saveRestaurant(restaurantRequestMapper.toDomain(request));
        return ResponseEntity.ok().build();

    }
}
