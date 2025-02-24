package com.pragma.food_court.adapters.driven.jpa.mysql.repository;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.DishEntity;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    @Query("SELECT d FROM DishEntity d WHERE (:category IS NULL OR :category = '' OR d.category = :category) AND d.restaurant.id = :restaurantId")
    Page<DishEntity> findByCategoryAndRestaurantId(@Param("category") String category, @Param("restaurantId") Long restaurantId, Pageable pageable);
}
