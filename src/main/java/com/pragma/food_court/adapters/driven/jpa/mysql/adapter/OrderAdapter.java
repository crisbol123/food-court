package com.pragma.food_court.adapters.driven.jpa.mysql.adapter;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.DishOrderEntity;
import com.pragma.food_court.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.food_court.adapters.driven.jpa.mysql.mapper.OrderEntityMapper;
import com.pragma.food_court.adapters.driven.jpa.mysql.repository.OrderRepository;
import com.pragma.food_court.domain.exception.InvalidOrderException;
import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.spi.IOrderPersistencePort;
import com.pragma.food_court.domain.util.PagedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
public class OrderAdapter implements IOrderPersistencePort {
    private final OrderRepository orderRepository;
  private final OrderEntityMapper orderEntityMapper;
    @Override
    public long saveOrder(Order order) {
        OrderEntity orderSave= orderEntityMapper.toEntity(order);
       orderSave.setId(null);
      OrderEntity orderEntity = orderRepository.save(orderSave);
      List<DishOrderEntity> dishOrderEntities = orderEntity.getDishes();
        for (DishOrderEntity dishOrderEntity : dishOrderEntities) {
            dishOrderEntity.setOrder(orderEntity);
        }
       return orderRepository.save(orderEntity).getId();

    }

    @Override
    public boolean hasOrder(long clientId) {
        return orderRepository.existsByClientIdAndReadyFalse(clientId);
    }

    @Override
    public PagedResponse<Order> getAllOrders(int page, int size, String state, Long restaurantId) {

        Pageable pageable = PageRequest.of(page, size);
        Page<OrderEntity> orderEntities = orderRepository.findAllOrders(restaurantId, state, pageable);
List<Order> orders = orderEntities.stream().map(orderEntityMapper::toDomain).collect(Collectors.toList());
        return new PagedResponse<>(orders,page, orderEntities.getTotalPages(), orderEntities.getTotalElements(), orderEntities.isLast());
    }

    @Override
    public void assignOrder(long orderId, long employeeId,String state) {
       OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
        orderEntity.setEmployeeId(employeeId);
        orderEntity.setState(state);
        orderRepository.save(orderEntity);
    }

    @Override
    public void changeState(long orderId, String state) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
        orderEntity.setState(state);
        orderRepository.save(orderEntity);
    }


    @Override
    public Order getOrder(long orderId) {
        Optional<OrderEntity> orderOptional = findById(orderId);
         if (orderOptional.isEmpty()){
             throw new InvalidOrderException("Order not found");
         }
        return  orderEntityMapper.toDomain(orderOptional.get());
    }

    @Override
    public List<Long> getOrdersIdByClientId(long clientId) {
        return orderRepository.findOrdersIdByClientId(clientId);
    }

    @Override
    public List<Long> getAllOrdersId() {
        return orderRepository.findAll().stream().map(OrderEntity::getId).collect(Collectors.toList());
    }

    @Override
    public Map<Long, Long> getOrdersIdAndEmployeeId() {
        return orderRepository.findAllByEmployeeIdIsNotNull().stream()
                .collect(Collectors.toMap(OrderEntity::getId, OrderEntity::getEmployeeId));
    }


    public Optional<OrderEntity> findById(long orderId) {
        return orderRepository.findById(orderId);
    }

}
