package com.example.zad.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateRestaurantRequestDto (
        @NotNull
        String name,

        @NotNull
        String city,

        @NotNull
        String cuisine
) { }
