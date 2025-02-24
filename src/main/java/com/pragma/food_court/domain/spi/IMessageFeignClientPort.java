package com.pragma.food_court.domain.spi;

public interface IMessageFeignClientPort {

    void sendMessage(Long orderId,Long clientId,String message);
    boolean verifySecurityCode(Long clientId, String securityCode);
}
