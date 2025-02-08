package com.pragma.food_court.adapters.driving.http.controller;

import com.pragma.food_court.adapters.driving.http.dto.request.RestaurantRequestDTO;
import com.pragma.food_court.domain.util.RestaurantResponseGetAll;
import com.pragma.food_court.adapters.driving.http.mapper.restaurant.IRestaurantRequestMapper;
import com.pragma.food_court.domain.api.IRestaurantServicePort;
import com.pragma.food_court.domain.util.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/get-all")
    public ResponseEntity<PagedResponse<RestaurantResponseGetAll>> getAllRestaurants(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam Boolean ascOrderByName) {

        return ResponseEntity.ok(restaurantServicePort.getAllRestaurants(page, size, ascOrderByName));
    }
}
