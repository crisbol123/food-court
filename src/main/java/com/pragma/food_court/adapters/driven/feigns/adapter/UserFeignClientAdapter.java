package com.pragma.food_court.adapters.driven.feigns.adapter;


import com.pragma.food_court.adapters.driven.feigns.clients.UserFeignClient;
import com.pragma.food_court.adapters.driven.feigns.dto.AuthorizationRequest;
import com.pragma.food_court.adapters.driven.feigns.dto.AuthorizationResponse;
import com.pragma.food_court.adapters.driven.feigns.dto.OwnerRequest;
import com.pragma.food_court.adapters.driven.feigns.dto.OwnerResponse;
import com.pragma.food_court.domain.spi.UserFeignClientPort;

public class UserFeignClientAdapter implements UserFeignClientPort {
    private final UserFeignClient userFeignClient;

    public UserFeignClientAdapter(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public boolean validateOwner(Long id) {
OwnerResponse ownerResponse = userFeignClient.validateOwner(new OwnerRequest(id));
        return  ownerResponse.isOwner();

    }

    @Override
    public AuthorizationResponse validateToken(String token) {
        return userFeignClient.validateToken(new AuthorizationRequest(token));
    }


}
