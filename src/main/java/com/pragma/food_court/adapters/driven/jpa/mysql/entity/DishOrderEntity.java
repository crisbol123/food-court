package com.pragma.food_court.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dish_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    private OrderEntity order;

    @Column(name = "dish_id", nullable = false)
    private Long dishId;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
