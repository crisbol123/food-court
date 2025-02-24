package com.pragma.food_court.adapters.driving.http.controller;

import com.pragma.food_court.adapters.driving.http.dto.request.NewEmployeeRequest;
import com.pragma.food_court.adapters.driving.http.dto.request.RestaurantRequestDTO;
import com.pragma.food_court.domain.util.RestaurantResponseGetAll;
import com.pragma.food_court.adapters.driving.http.mapper.IRestaurantRequestMapper;
import com.pragma.food_court.domain.api.IRestaurantServicePort;
import com.pragma.food_court.domain.util.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pragma.food_court.configuration.Constants.*;

@RestController
@RequestMapping(RESTAURANT_BASE_PATH)
@RequiredArgsConstructor
@Tag(name = RESTAURANT_TAG, description = RESTAURANT_DESCRIPTION)
public class RestaurantRestController {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;

    @PostMapping(RESTAURANT_SAVE_PATH)
    @Operation(summary = RESTAURANT_SAVE_SUMMARY, description = RESTAURANT_SAVE_DESCRIPTION)
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequestDTO request) {
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toDomain(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping(RESTAURANT_GET_ALL_PATH)
    @Operation(summary = RESTAURANT_GET_ALL_SUMMARY, description = RESTAURANT_GET_ALL_DESCRIPTION)
    public ResponseEntity<PagedResponse<RestaurantResponseGetAll>> getAllRestaurants(
            @RequestParam(PAGE_PARAM) Integer page,
            @RequestParam(SIZE_PARAM) Integer size,
            @RequestParam(ASC_ORDER_BY_NAME_PARAM) Boolean ascOrderByName) {
        return ResponseEntity.ok(restaurantServicePort.getAllRestaurants(page, size, ascOrderByName));
    }

    @PostMapping(RESTAURANT_CREATE_EMPLOYEE_PATH)
    @Operation(summary = RESTAURANT_CREATE_EMPLOYEE_SUMMARY, description = RESTAURANT_CREATE_EMPLOYEE_DESCRIPTION)
    public void createEmployee(@RequestBody NewEmployeeRequest request) {
        restaurantServicePort.createEmployee(request.getRestaurantId(), request.getEmployeeId());
    }
}
