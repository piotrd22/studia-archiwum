package com.example.zad.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRestaurantDto {
    @Past
    private LocalDate foundingDate;

    @Min(1)
    private Integer numberOfEmployees;

    private Boolean isMichelinStarred;
    private String name;
    private String cuisine;
}
