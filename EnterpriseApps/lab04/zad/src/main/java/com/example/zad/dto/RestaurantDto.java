package com.example.zad.dto;

import java.util.UUID;

public record RestaurantDto(
        UUID id,
        String name,
        String city,
        String cuisine
) { }
