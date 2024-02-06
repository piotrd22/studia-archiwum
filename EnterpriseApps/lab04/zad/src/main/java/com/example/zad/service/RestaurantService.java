package com.example.zad.service;

import com.example.zad.dto.CreateRestaurantRequestDto;
import com.example.zad.dto.RestaurantDto;
import com.example.zad.dto.UpdateRestaurantRequestDto;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    List<RestaurantDto> getAllRestaurants();
    RestaurantDto getRestaurantById(UUID id);
    RestaurantDto createRestaurant(CreateRestaurantRequestDto dto);
    RestaurantDto updateRestaurant(UpdateRestaurantRequestDto dto, UUID id);
    void deleteRestaurant(UUID id);
}
