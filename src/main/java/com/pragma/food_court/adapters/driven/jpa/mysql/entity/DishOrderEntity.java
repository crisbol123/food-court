package com.pragma.food_court.adapters.driven.jpa.mysql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = true)
    @JsonIgnore
    private OrderEntity order;

    @Column(name = "dish_id", nullable = true)
    private Long dishId;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
