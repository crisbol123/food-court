package com.pragma.food_court.adapters.driving.http.controller;



import com.pragma.food_court.adapters.driving.http.dto.request.SaveOrderRequestDTO;
import com.pragma.food_court.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.food_court.domain.api.IOrderServicePort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderRestController {
    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    @PostMapping("/save")
    public ResponseEntity<Void> saveOrder(@RequestBody SaveOrderRequestDTO request) {

        orderServicePort.saveOrder(orderRequestMapper.toDomain(request));
        return ResponseEntity.ok().build();

    }
}
