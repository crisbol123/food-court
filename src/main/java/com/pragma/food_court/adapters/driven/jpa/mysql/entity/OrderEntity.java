package com.pragma.food_court.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private long restaurantId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishOrderEntity> dishes;

    @Column(name = "client_id", nullable = false)
    private long clientId;

    @Column(name = "employee_id", nullable = false)
    private long employeeId;

    @Column(name = "state", nullable = false)
    private String state;
}
