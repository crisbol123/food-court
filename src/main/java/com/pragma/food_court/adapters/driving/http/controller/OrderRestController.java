package com.pragma.food_court.adapters.driving.http.controller;



import com.pragma.food_court.adapters.driving.http.dto.request.SaveOrderRequestDTO;
import com.pragma.food_court.adapters.driving.http.dto.response.OrderResponse;
import com.pragma.food_court.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.food_court.domain.api.IOrderServicePort;
import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.util.PagedResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getAllOrdersByClientId")
    public PagedResponse<OrderResponse> getAllOrdersByClientId( @RequestParam("page") int page, @RequestParam("size") int size) {
        PagedResponse<Order> orderPagedResponse = orderServicePort.getAllOrdersByClientId( page, size);
        List<OrderResponse> orderResponses = orderRequestMapper.toResponse(orderPagedResponse.getContent());
        return new PagedResponse<>(orderResponses, orderPagedResponse.getCurrentPage(), orderPagedResponse.getTotalPages(), orderPagedResponse.getTotalElements(), orderPagedResponse.isLastPage());
    }
    @PutMapping("/assignOrder")
    public ResponseEntity<Void> assignOrder(@RequestParam("orderId") long orderId)
    {
        orderServicePort.assignOrder(orderId);

        return ResponseEntity.ok().build();
    }
    @PutMapping("/notifyReadyOrder")
    public ResponseEntity<Void> notifyReadyOrder(@RequestParam("orderId") long orderId)
    {
        orderServicePort.notifyOrderReady(orderId);

        return ResponseEntity.ok().build();
    }
}
