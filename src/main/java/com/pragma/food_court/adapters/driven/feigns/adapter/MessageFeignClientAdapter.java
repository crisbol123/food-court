package com.pragma.food_court.adapters.driven.feigns.adapter;

import com.pragma.food_court.adapters.driven.feigns.clients.MessageFeignClient;
import com.pragma.food_court.adapters.driven.feigns.dto.SendMessageDTO;
import com.pragma.food_court.domain.spi.IMessageFeignClientPort;

public class MessageFeignClientAdapter implements IMessageFeignClientPort {
    private final MessageFeignClient messageFeignClient;

    public MessageFeignClientAdapter(MessageFeignClient messageFeignClient) {
        this.messageFeignClient = messageFeignClient;
    }

    @Override
    public void sendMessage(Long orderId,Long clientId, String message) {
        messageFeignClient.sendMessageReady(new SendMessageDTO(orderId,clientId, message));
    }

    @Override
    public boolean verifySecurityCode(Long orderId, String securityCode) {
        return messageFeignClient.verifySecurityCode(orderId, securityCode);
    }
}
