package com.pragma.food_court.adapters.driving.http.controller;



import com.pragma.food_court.adapters.driving.http.dto.request.SaveOrderRequestDTO;
import com.pragma.food_court.adapters.driving.http.dto.response.OrderResponse;
import com.pragma.food_court.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.food_court.domain.api.IOrderServicePort;
import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.util.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.pragma.food_court.configuration.Constants.*;

@RestController
@RequestMapping(ORDER_BASE_PATH)
@AllArgsConstructor
@Tag(name = ORDER_TAG, description = ORDER_DESCRIPTION)
public class OrderRestController {
    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;

    @PostMapping(ORDER_SAVE_PATH)
    @Operation(summary = ORDER_SAVE_SUMMARY, description = ORDER_SAVE_DESCRIPTION)
    public ResponseEntity<Void> saveOrder(@RequestBody SaveOrderRequestDTO request) {
        orderServicePort.saveOrder(orderRequestMapper.toDomain(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping(ORDER_GET_ALL_PATH)
    @Operation(summary = ORDER_GET_ALL_SUMMARY, description = ORDER_GET_ALL_DESCRIPTION)
    public PagedResponse<OrderResponse> getAllOrders(@RequestParam(PAGE_PARAM) int page,
                                                     @RequestParam(SIZE_PARAM) int size,
                                                     @RequestParam(STATUS_PARAM) String status) {
        PagedResponse<Order> orderPagedResponse = orderServicePort.getAllOrders(page, size, status);
        List<OrderResponse> orderResponses = orderRequestMapper.toResponse(orderPagedResponse.getContent());
        return new PagedResponse<>(orderResponses, orderPagedResponse.getCurrentPage(),
                orderPagedResponse.getTotalPages(), orderPagedResponse.getTotalElements(),
                orderPagedResponse.isLastPage());
    }

    @PutMapping(ORDER_ASSIGN_PATH)
    @Operation(summary = ORDER_ASSIGN_SUMMARY, description = ORDER_ASSIGN_DESCRIPTION)
    public ResponseEntity<Void> assignOrder(@RequestParam(ORDER_ID_PARAM) long orderId) {
        orderServicePort.assignOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(ORDER_NOTIFY_READY_PATH)
    @Operation(summary = ORDER_NOTIFY_READY_SUMMARY, description = ORDER_NOTIFY_READY_DESCRIPTION)
    public ResponseEntity<Void> notifyReadyOrder(@RequestParam(ORDER_ID_PARAM) long orderId) {
        orderServicePort.notifyOrderReady(orderId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(ORDER_DELIVER_PATH)
    @Operation(summary = ORDER_DELIVER_SUMMARY, description = ORDER_DELIVER_DESCRIPTION)
    public ResponseEntity<Void> deliverOrder(@RequestParam(ORDER_ID_PARAM) long orderId,
                                             @RequestParam(SECURITY_CODE_PARAM) String securityCode) {
        orderServicePort.deliverOrder(orderId, securityCode);
        return ResponseEntity.ok().build();
    }

    @PutMapping(ORDER_CANCEL_PATH)
    @Operation(summary = ORDER_CANCEL_SUMMARY, description = ORDER_CANCEL_DESCRIPTION)
    public ResponseEntity<Void> cancelOrder(@RequestParam(ORDER_ID_PARAM) long orderId) {
        orderServicePort.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(ORDER_GET_BY_CLIENT_ID_PATH)
    @Operation(summary = ORDER_GET_BY_CLIENT_ID_SUMMARY, description = ORDER_GET_BY_CLIENT_ID_DESCRIPTION)
    public List<Long> getOrdersIdByClientId(@RequestParam(CLIENT_ID_PARAM) long clientId) {
        return orderServicePort.getOrdersIdByClientId(clientId);
    }

    @GetMapping(ORDER_GET_ALL_IDS_PATH)
    @Operation(summary = ORDER_GET_ALL_IDS_SUMMARY, description = ORDER_GET_ALL_IDS_DESCRIPTION)
    public List<Long> getAllOrdersId() {
        return orderServicePort.getAllOrdersId();
    }

    @GetMapping(ORDER_GET_ID_AND_EMPLOYEE_ID_PATH)
    @Operation(summary = ORDER_GET_ID_AND_EMPLOYEE_ID_SUMMARY, description = ORDER_GET_ID_AND_EMPLOYEE_ID_DESCRIPTION)
    public Map<Long, Long> getOrdersIdAndEmployeeId() {
        return orderServicePort.getOrdersIdAndEmployeeId();
    }
}
