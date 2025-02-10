package com.pragma.food_court.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.EmployeeRestaurantEntity;
import com.pragma.food_court.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.IEmployeeRestaurantRepository;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.pragma.food_court.domain.model.Restaurant;
import com.pragma.food_court.domain.spi.IRestaurantPersistencePort;
import com.pragma.food_court.domain.util.PagedResponse;
import com.pragma.food_court.domain.util.RestaurantResponseGetAll;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RestaurantAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper  restaurantEntityMapper;
    private final IEmployeeRestaurantRepository employeeRestaurantRepository;
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

    @Override
    public PagedResponse<RestaurantResponseGetAll> getAllRestaurants(int page, int size, boolean ascOrder) {
        Sort sort = ascOrder ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pagination = PageRequest.of(page, size, sort);
        Page<RestaurantEntity> restaurantPage = restaurantRepository.findAll(pagination);
        List<RestaurantEntity> restaurantEntities = restaurantPage.getContent();

        List<RestaurantResponseGetAll> restaurants = restaurantEntities.stream()
                .map(restaurantEntity -> new RestaurantResponseGetAll(restaurantEntity.getName(), restaurantEntity.getLogoUrl())
                ).toList();
long totalItems = restaurantPage.getTotalElements();
        int totalPages = restaurantPage.getTotalPages();
        return new PagedResponse<>(restaurants, page, totalPages,totalItems,restaurantPage.isLast());
    }

    @Override
    public void createEmployee(long restaurantId, long employeeId) {
        EmployeeRestaurantEntity employeeRestaurantEntity = new EmployeeRestaurantEntity( restaurantId, employeeId);
        employeeRestaurantRepository.save(employeeRestaurantEntity);
    }


}
