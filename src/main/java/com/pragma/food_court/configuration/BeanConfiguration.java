package com.pragma.food_court.configuration;


import com.pragma.food_court.adapters.driven.feigns.adapter.TraceabilityFeignClientAdapter;
import com.pragma.food_court.adapters.driven.feigns.adapter.UserFeignClientAdapter;
import com.pragma.food_court.adapters.driven.feigns.clients.TraceabilityFeignClient;
import com.pragma.food_court.adapters.driven.feigns.clients.UserFeignClient;
import com.pragma.food_court.adapters.driven.jpa.mysql.adapter.DishAdapter;
import com.pragma.food_court.adapters.driven.jpa.mysql.adapter.OrderAdapter;
import com.pragma.food_court.adapters.driven.jpa.mysql.adapter.RestaurantAdapter;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.IDishEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.OrderEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.IDishRepository;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.IEmployeeRestaurantRepository;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.OrderRepository;
import com.pragma.food_court.configuration.securityconfig.SecurityContextPortImpl;
import com.pragma.food_court.domain.api.IDishServicePort;
import com.pragma.food_court.domain.api.IOrderServicePort;
import com.pragma.food_court.domain.api.IRestaurantServicePort;
import com.pragma.food_court.domain.spi.*;
import com.pragma.food_court.domain.use_cases.DishUseCase;
import com.pragma.food_court.domain.use_cases.OrderUseCase;
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
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IEmployeeRestaurantRepository employeeRestaurantRepository;
   private final OrderRepository orderRepository;
private final OrderEntityMapper orderEntityMapper;
private final TraceabilityFeignClient traceabilityFeignClient;

@Bean
public ITraceabilityFeignClientPort traceabilityFeignClientPort() {
    return new TraceabilityFeignClientAdapter(traceabilityFeignClient);
}

    @Bean
    public ISecurityContextPort securityContextPort(){
        return new SecurityContextPortImpl();
    }

@Bean
public IRestaurantPersistencePort restaurantPersistencePort() {
    return new RestaurantAdapter(restaurantRepository, restaurantEntityMapper, employeeRestaurantRepository);
}
@Bean
public IOrderPersistencePort orderPersistencePort() {
    return new OrderAdapter(orderRepository, orderEntityMapper);
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
public IOrderServicePort orderServicePort() {
    return new OrderUseCase(orderPersistencePort(), securityContextPort(), traceabilityFeignClientPort(), restaurantServicePort());
}
@Bean
public IRestaurantServicePort restaurantServicePort() {
    return new RestaurantUseCase(userFeignClientPort(), restaurantPersistencePort());
}
    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishAdapter(restaurantPersistencePort(), dishEntityMapper,dishRepository);
    }

@Bean
public IDishServicePort dishServicePort() {
    return new DishUseCase( restaurantPersistencePort(), dishPersistencePort(), securityContextPort());





}
}

