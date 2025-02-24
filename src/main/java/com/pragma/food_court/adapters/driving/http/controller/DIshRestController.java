package com.pragma.food_court.adapters.driving.http.controller;


import com.pragma.food_court.adapters.driving.http.dto.request.DishRequestDTO;
import com.pragma.food_court.adapters.driving.http.dto.request.UpdateDishRequestDTO;
import com.pragma.food_court.adapters.driving.http.dto.response.DishResponse;
import com.pragma.food_court.adapters.driving.http.mapper.IDishRequestMapper;
import com.pragma.food_court.domain.api.IDishServicePort;
import com.pragma.food_court.domain.model.Dish;
import com.pragma.food_court.domain.util.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pragma.food_court.configuration.Constants.*;

@RestController
@AllArgsConstructor
@RequestMapping(DISH_PATH)
@Tag(name = DISH_MANAGEMENT_TAG, description = DISH_MANAGEMENT_DESCRIPTION)
public class DIshRestController {
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;

    @PostMapping(CREATE_DISH_PATH)
    @Operation(summary = CREATE_DISH_SUMMARY, description = CREATE_DISH_DESCRIPTION)
    public void createDish(@RequestBody DishRequestDTO request) {
        dishServicePort.createDish(dishRequestMapper.toDomain(request));
    }

    @PutMapping(ENABLE_DISABLE_DISH_PATH)
    @Operation(summary = ENABLE_DISABLE_DISH_SUMMARY, description = ENABLE_DISABLE_DISH_DESCRIPTION)
    public void enableDisableDish(@PathVariable Long id) {
        dishServicePort.enableDisableDish(id);
    }

    @PutMapping(UPDATE_DISH_PATH)
    @Operation(summary = UPDATE_DISH_SUMMARY, description = UPDATE_DISH_DESCRIPTION)
    public void updateDish(@PathVariable Long id, @RequestBody UpdateDishRequestDTO request) {
        Dish dish = new Dish();
        dish.setPrice(request.getPrice());
        dish.setDescription(request.getDescription());
        dish.setId(id);
        dishServicePort.updateDish(dish);
    }

    @GetMapping(GET_ALL_DISHES_PATH)
    @Operation(summary = GET_ALL_DISHES_SUMMARY, description = GET_ALL_DISHES_DESCRIPTION)
    public PagedResponse<DishResponse> getAllDishes(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String category, @RequestParam long restaurantId) {
        PagedResponse<Dish> dishes = dishServicePort.getAllDishes(page, size, category, restaurantId);
        List<DishResponse> dishResponses = dishRequestMapper.toDTOList(dishes.getContent());
        return new PagedResponse<>(dishResponses, dishes.getCurrentPage(), dishes.getTotalPages(), dishes.getTotalElements(), dishes.isLastPage());
    }
}
