package com.pragma.food_court.adapters.driven.feigns.clients;

import com.pragma.food_court.adapters.driven.feigns.FeignClientConfig;
import com.pragma.food_court.adapters.driven.feigns.dto.SendMessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "message", url = "${message.service.url}", configuration = FeignClientConfig.class)

public interface MessageFeignClient {
    @PostMapping("/message/send-message-ready")
    void sendMessageReady(@RequestBody SendMessageDTO sendMessageDTO);

    @PostMapping("/message/send-message-canceled")
    void sendMessageCanceled(@RequestBody SendMessageDTO sendMessageDTO);
    @GetMapping("/message/verify-security-code")
    boolean verifySecurityCode(@RequestParam Long orderId, @RequestParam String securityCode);
}
