package com.example.zad.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateRestaurantRequestDto (
        @NotBlank
        String name,

        @NotBlank
        String city,

        @NotBlank
        String cuisine
) { }
