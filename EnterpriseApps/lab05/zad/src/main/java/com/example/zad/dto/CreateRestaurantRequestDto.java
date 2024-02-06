package com.example.zad.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CreateRestaurantRequestDto (
        @NotBlank
        String name,

        @NotBlank
        String city,

        @NotBlank
        String cuisine,

        @NotNull
        boolean hasMichelinStar,

        @Min(1)
        int numberOfEmployees,

        @NotNull
        @Past
        LocalDate dateFounded
) { }
