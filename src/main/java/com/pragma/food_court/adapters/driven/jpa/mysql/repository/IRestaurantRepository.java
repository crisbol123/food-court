package com.pragma.food_court.adapters.driven.jpa.mysql.repository;

import com.pragma.food_court.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    @Query("SELECT r.ownerId FROM RestaurantEntity r WHERE r.id = :id")
    Optional<Long> findOwnerIdById(@Param("id") Long id);

}
