package com.pragma.food_court.domain.spi;

public interface ITraceabilityFeignClientPort {
    void saveState(String state, long orderId);
}
