package com.pragma.food_court.adapters.driving.http.controller;

import com.pragma.food_court.adapters.driving.http.dto.request.DishRequestDTO;
import com.pragma.food_court.adapters.driving.http.dto.request.UpdateDishRequestDTO;
import com.pragma.food_court.adapters.driving.http.dto.response.DishResponse;
import com.pragma.food_court.adapters.driving.http.mapper.IDishRequestMapper;
import com.pragma.food_court.domain.api.IDishServicePort;
import com.pragma.food_court.domain.model.Dish;
import com.pragma.food_court.domain.util.PagedResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all")
    public PagedResponse<DishResponse> getAllDishes(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String category, @RequestParam long restaurantId) {

        PagedResponse<Dish> dishes= dishServicePort.getAllDishes(page, size, category, restaurantId);
        List<DishResponse> dishResponses = dishRequestMapper.toDTOList(dishes.getContent());
        return new PagedResponse<>(dishResponses, dishes.getCurrentPage(), dishes.getTotalPages(), dishes.getTotalElements(), dishes.isLastPage());

    }
}
