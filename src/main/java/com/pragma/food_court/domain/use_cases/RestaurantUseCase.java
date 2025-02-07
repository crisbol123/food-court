package com.pragma.food_court.domain.use_cases;


import com.pragma.food_court.domain.api.IRestaurantServicePort;
import com.pragma.food_court.domain.exception.InvalidOwnerException;
import com.pragma.food_court.domain.model.Restaurant;
import com.pragma.food_court.domain.spi.IRestaurantPersistencePort;
import com.pragma.food_court.domain.spi.UserFeignClientPort;

public class RestaurantUseCase implements IRestaurantServicePort {
private final UserFeignClientPort userFeignClientPort;
private final IRestaurantPersistencePort iRestaurantPersistencePort;

    public RestaurantUseCase(UserFeignClientPort userFeignClientPort, IRestaurantPersistencePort iRestaurantPersistencePort) {
        this.userFeignClientPort = userFeignClientPort;
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
if(userFeignClientPort.validateOwner(restaurant.getOwnerId())){
iRestaurantPersistencePort.saveRestaurant(restaurant);
        }else{
throw new InvalidOwnerException();
        }
    }
}


