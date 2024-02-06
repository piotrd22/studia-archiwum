package com.example.zad.service;

import com.example.zad.domain.Restaurant;
import com.example.zad.dto.CreateRestaurantRequestDto;
import com.example.zad.dto.RestaurantDto;
import com.example.zad.dto.UpdateRestaurantRequestDto;
import com.example.zad.exception.RestaurantNotFoundException;
import com.example.zad.mapper.RestaurantMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final List<Restaurant> db;
    private final RestaurantMapper mapper;

    public RestaurantServiceImpl(List<Restaurant> db, RestaurantMapper mapper) {
        this.db = db;
        this.mapper = mapper;
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        return db.stream()
                .map(mapper::restaurantToRestaurantDto)
                .toList();
    }

    @Override
    public RestaurantDto getRestaurantById(UUID id) {
        Restaurant restaurant = db.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        return mapper.restaurantToRestaurantDto(restaurant);
    }

    @Override
    public RestaurantDto createRestaurant(CreateRestaurantRequestDto dto) {
        Restaurant restaurant = mapper.createRestaurantRequestDtoToRestaurant(dto);
        restaurant.setId(UUID.randomUUID());
        db.add(restaurant);
        return mapper.restaurantToRestaurantDto(restaurant);
    }

    @Override
    public RestaurantDto updateRestaurant(UpdateRestaurantRequestDto dto, UUID id) {
        Restaurant restaurant = db.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant = mapper.updateRestaurantRequestDtoToRestaurant(dto, restaurant);
        db.add(restaurant);
        return mapper.restaurantToRestaurantDto(restaurant);
    }

    @Override
    public void deleteRestaurant(UUID id) {
       boolean removed = db.removeIf(r -> r.getId().equals(id));
        if (!removed) {
            throw new RestaurantNotFoundException(id);
        }
    }
}
