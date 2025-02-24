package com.pragma.food_court.adapters.driven.feigns.clients;

import com.pragma.food_court.adapters.driven.feigns.FeignClientConfig;
import com.pragma.food_court.adapters.driven.feigns.dto.StateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "traceability", url = "${traceability.service.url}", configuration = FeignClientConfig.class)
public interface TraceabilityFeignClient {
    @PostMapping("/state/save")
    void saveState(@RequestBody StateDTO stateDTO);

}
