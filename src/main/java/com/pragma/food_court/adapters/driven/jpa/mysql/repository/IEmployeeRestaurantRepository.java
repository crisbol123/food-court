package com.pragma.food_court.adapters.driven.jpa.mysql.repository;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.EmployeeRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IEmployeeRestaurantRepository extends JpaRepository<EmployeeRestaurantEntity, Long> {

}
