package com.example.zad.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAgreementDto {
    @NotNull
    private Long restaurantId;

    @NotNull
    private Long supplierId;
}
