package com.pragma.food_court.adapters.driven.jpa.mysql.repository;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.OrderEntity;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrderEntity o WHERE o.clientId = :clientId AND o.state <> 'DELIVERED' AND o.state <> 'CANCELED'")
    Boolean existsByClientIdAndReadyFalse(@Param("clientId") Long clientId);
@Query("SELECT o FROM OrderEntity o WHERE o.restaurantId = :restaurantId AND o.state = :state")
Page<OrderEntity> findAllOrders(@Param("restaurantId") Long restaurantId,@Param("state") String state, Pageable pageable);

    @Query("SELECT o.id FROM OrderEntity o WHERE o.clientId = :clientId")
    List<Long> findOrdersIdByClientId(@Param("clientId") Long clientId);

    @Query("SELECT o FROM OrderEntity o WHERE o.employeeId IS NOT NULL AND o.employeeId <> 0")
    List<OrderEntity> findAllByEmployeeIdIsNotNull();


}