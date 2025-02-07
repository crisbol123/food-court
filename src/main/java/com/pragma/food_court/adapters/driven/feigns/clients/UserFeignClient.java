package com.pragma.food_court.adapters.driven.feigns.clients;



import com.pragma.food_court.adapters.driven.feigns.FeignClientConfig;
import com.pragma.food_court.adapters.driven.feigns.dto.AuthorizationRequest;
import com.pragma.food_court.adapters.driven.feigns.dto.AuthorizationResponse;
import com.pragma.food_court.adapters.driven.feigns.dto.OwnerRequest;
import com.pragma.food_court.adapters.driven.feigns.dto.OwnerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user", url = "${usuario.service.url}", configuration = FeignClientConfig.class)
public interface UserFeignClient {
    @GetMapping("/auth-user/validate-owner")
    OwnerResponse validateOwner(@RequestBody OwnerRequest ownerRequest);

    @GetMapping("/auth-user/validate-token")
    AuthorizationResponse validateToken(@RequestBody AuthorizationRequest authorizationRequest);

}