package com.pragma.food_court.adapters.driven.jpa.mysql.repository;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.OrderEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrderEntity o WHERE o.clientId = :clientId AND o.state <> 'READY'")
    Boolean existsByClientIdAndReadyFalse(@Param("clientId") Long clientId);
}
