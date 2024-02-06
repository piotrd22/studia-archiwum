package com.example.zad.services;

import com.example.zad.domain.Address;
import com.example.zad.domain.Restaurant;
import com.example.zad.dtos.request.RestaurantFilterDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> searchRestaurants(RestaurantFilterDto filterDto, Pageable pageable);
    List<Restaurant> getRestaurantsBySupplierId(Long supplierId, Pageable pageable);
    Restaurant getRestaurantById(Long id);
    Restaurant addRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Restaurant updatedRestaurant);
    void deleteRestaurant(Long id);
    Restaurant addAddressToRestaurant(Long id, Address address);
    Restaurant updateAddressInRestaurant(Long id, Address address);
}
