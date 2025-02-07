package com.pragma.food_court.domain.use_cases;

import com.pragma.food_court.domain.api.IDishServicePort;
import com.pragma.food_court.domain.exception.InvalidDishException;
import com.pragma.food_court.domain.exception.InvalidRestaurantException;
import com.pragma.food_court.domain.model.Dish;
import com.pragma.food_court.domain.spi.IDishPersistencePort;
import com.pragma.food_court.domain.spi.IRestaurantPersistencePort;
import com.pragma.food_court.domain.spi.ISecurityContextPort;

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
            iDishPersistencePort.createDish(dish);
        }
        else{
            throw new InvalidRestaurantException("Restaurant with id " + dish.getRestaurantId() + " does not exist");
        }
    }

    @Override
    public void updateDish(Dish dish) {
       Optional<Dish> dishToUpdate = iDishPersistencePort.findById(dish.getId());
         if(dishToUpdate.isEmpty()){
              throw new InvalidDishException("Dish with id " + dish.getId() + " does not exist");
         }
Optional<Long> ownerId = iRestaurantPersistencePort.findOwnerIdByRestaurantId(dishToUpdate.get().getRestaurantId());
      if(ownerId.isEmpty()){
          throw new InvalidRestaurantException("Restaurant with id " + dish.getRestaurantId() + " does not exist");
        }
        if(jwtSecurityContextPort.getUserId().equals(ownerId.get())){
            iDishPersistencePort.updateDish(dish);
        }
        else{
            throw new InvalidRestaurantException("You are not the owner of the restaurant with id " + dish.getRestaurantId());
        }
    }


}
