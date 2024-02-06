package com.example.zad.dto;

import java.time.LocalDate;
import java.util.UUID;

public record RestaurantDto(
        UUID id,
        String name,
        String city,
        String cuisine,
        boolean hasMichelinStar,
        int numberOfEmployees,
        LocalDate dateFounded
) { }
