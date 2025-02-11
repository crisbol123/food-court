package com.pragma.food_court.adapters.driven.jpa.mysql.repository;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.EmployeeRestaurantEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmployeeRestaurantRepository extends JpaRepository<EmployeeRestaurantEntity, Long> {
   @Query("SELECT er.restaurantId FROM EmployeeRestaurantEntity er WHERE er.employeeId = :employeeId")
    Long getRestaurantIdByEmployeeId(@Param("employeeId") Long employeeId);
}
