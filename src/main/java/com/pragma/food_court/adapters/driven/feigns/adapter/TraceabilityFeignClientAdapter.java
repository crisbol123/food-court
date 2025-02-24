package com.pragma.food_court.adapters.driven.feigns.adapter;

import com.pragma.food_court.adapters.driven.feigns.clients.TraceabilityFeignClient;
import com.pragma.food_court.adapters.driven.feigns.dto.StateDTO;
import com.pragma.food_court.domain.spi.ITraceabilityFeignClientPort;

public class TraceabilityFeignClientAdapter implements ITraceabilityFeignClientPort {
    private final TraceabilityFeignClient traceabilityFeignClient;

    public TraceabilityFeignClientAdapter(TraceabilityFeignClient traceabilityFeignClient) {
        this.traceabilityFeignClient = traceabilityFeignClient;
    }

    @Override
    public void saveState(String state, long orderId) {
        StateDTO stateDTO = new StateDTO(orderId, state);
        traceabilityFeignClient.saveState(stateDTO);
    }
}
