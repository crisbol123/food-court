package com.pragma.food_court.adapters.driving.http.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewEmployeeRequest {
    private long restaurantId;
    private long employeeId;
}
