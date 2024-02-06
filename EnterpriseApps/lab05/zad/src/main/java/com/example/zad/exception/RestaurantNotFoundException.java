package com.example.zad.exception;

import java.util.UUID;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(UUID id) {
        super("Restaurant with id '%s' not found".formatted(id));
    }
}
