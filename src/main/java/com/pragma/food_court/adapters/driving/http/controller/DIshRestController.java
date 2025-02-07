package com.pragma.food_court.adapters.driving.http.controller;

import com.pragma.food_court.adapters.driving.http.dto.request.DishRequestDTO;
import com.pragma.food_court.adapters.driving.http.dto.request.UpdateDishRequestDTO;
import com.pragma.food_court.adapters.driving.http.mapper.restaurant.IDishRequestMapper;
import com.pragma.food_court.domain.api.IDishServicePort;
import com.pragma.food_court.domain.model.Dish;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/dish")
public class DIshRestController {
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    @PostMapping("/create")
    public void createDish(@RequestBody DishRequestDTO request) {
dishServicePort.createDish( dishRequestMapper.toDomain(request)) ;
    }
    @PutMapping("enable-disable/{id}")
public void enableDisableDish(@PathVariable Long id) {
dishServicePort.enableDisableDish(id);
    }

    @PutMapping("/update/{id}")
    public void updateDish(@PathVariable Long id, @RequestBody UpdateDishRequestDTO request) {
  Dish dish = new Dish();
    dish.setPrice(request.getPrice());
    dish.setDescription(request.getDescription());
        dish.setId(id);
        dishServicePort.updateDish(dish);
    }
}
