package com.pragma.food_court.domain.use_cases;

import com.pragma.food_court.adapters.driving.http.dto.response.DishResponse;
import com.pragma.food_court.domain.api.IDishServicePort;
import com.pragma.food_court.domain.exception.InvalidDishException;
import com.pragma.food_court.domain.exception.InvalidOwnerException;
import com.pragma.food_court.domain.exception.InvalidRestaurantException;
import com.pragma.food_court.domain.model.Dish;
import com.pragma.food_court.domain.spi.IDishPersistencePort;
import com.pragma.food_court.domain.spi.IRestaurantPersistencePort;
import com.pragma.food_court.domain.spi.ISecurityContextPort;
import com.pragma.food_court.domain.util.PagedResponse;

import java.util.Optional;

public class DishUseCase implements IDishServicePort {
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IDishPersistencePort iDishPersistencePort;
    private final ISecurityContextPort jwtSecurityContextPort;

    public DishUseCase(IRestaurantPersistencePort iRestaurantPersistencePort, IDishPersistencePort iDishPersistencePort, ISecurityContextPort jwtSecurityContextPort) {
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.iDishPersistencePort = iDishPersistencePort;
        this.jwtSecurityContextPort = jwtSecurityContextPort;

    }
    @Override
    public void createDish(Dish dish) {
        if(iRestaurantPersistencePort.existsById(dish.getRestaurantId()) ){
            iDishPersistencePort.saveDish(dish);
        }
        else{
            throw new InvalidRestaurantException("Restaurant with id " + dish.getRestaurantId() + "does not exist");
        }
    }

    @Override
    public void updateDish(Dish dish) {
        Dish dishToUpdate = validateDishAndOwnership(dish.getId());
            iDishPersistencePort.saveDish(dishToUpdate);
    }

    @Override
    public void enableDisableDish(Long id) {
        Dish dishToUpdate = validateDishAndOwnership(id);
        dishToUpdate.setActive(!dishToUpdate.isActive());
        iDishPersistencePort.saveDish(dishToUpdate);
    }

    @Override
    public PagedResponse<Dish> getAllDishes(int page, int size, String category, long restaurantId) {

        return iDishPersistencePort.getAllDishes(page, size, category, restaurantId);
    }

    private Dish validateDishAndOwnership(Long dishId) {
        Optional<Dish> dishToUpdate = iDishPersistencePort.findById(dishId);
        if (dishToUpdate.isEmpty()) {
            throw new InvalidDishException("Dish with id " + dishId + " does not exist");
        }
        Optional<Long> ownerId = iRestaurantPersistencePort.findOwnerIdByRestaurantId(dishToUpdate.get().getRestaurantId());
        if (ownerId.isEmpty()) {
            throw new InvalidRestaurantException("Restaurant with id " + dishToUpdate.get().getRestaurantId() + " does not exist");
        }
        if (!jwtSecurityContextPort.getUserId().equals(ownerId.get())) {
            throw new InvalidOwnerException("You are not the owner of the restaurant with id " + dishToUpdate.get().getRestaurantId());
        }
        return dishToUpdate.get();
    }


}
