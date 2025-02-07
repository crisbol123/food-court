package com.pragma.food_court.domain.spi;


import com.pragma.food_court.adapters.driven.feigns.dto.AuthorizationResponse;

public interface UserFeignClientPort {
    boolean validateOwner(Long id);

    AuthorizationResponse validateToken(String token);
}
