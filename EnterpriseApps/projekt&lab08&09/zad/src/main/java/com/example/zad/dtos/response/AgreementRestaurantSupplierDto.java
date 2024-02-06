package com.example.zad.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgreementRestaurantSupplierDto {
    private Long id;
    private RestaurantDto restaurant;
    private SupplierDto supplier;
    private LocalDate agreementDate;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
