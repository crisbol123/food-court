package com.pragma.food_court.adapters.driven.feigns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
    private long orderId;
    private String state;
}
