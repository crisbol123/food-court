package com.pragma.food_court.configuration;


import com.pragma.food_court.adapters.driven.feigns.adapter.UserFeignClientAdapter;
import com.pragma.food_court.adapters.driven.feigns.clients.UserFeignClient;
import com.pragma.food_court.adapters.driven.jpa.mysql.adapter.RestaurantAdapter;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.pragma.food_court.domain.api.IRestaurantServicePort;
import com.pragma.food_court.domain.spi.IRestaurantPersistencePort;
import com.pragma.food_court.domain.spi.UserFeignClientPort;
import com.pragma.food_court.domain.use_cases.RestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {


    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final UserFeignClient userFeignClient;

@Bean
public IRestaurantPersistencePort restaurantPersistencePort() {
    return new RestaurantAdapter(restaurantRepository, restaurantEntityMapper);
}

    @Bean
    public UserFeignClientPort userFeignClientPort() {
        return new UserFeignClientAdapter(userFeignClient);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

@Bean
public IRestaurantServicePort articleServicePort() {
    return new RestaurantUseCase( userFeignClientPort(), restaurantPersistencePort());
}






}

