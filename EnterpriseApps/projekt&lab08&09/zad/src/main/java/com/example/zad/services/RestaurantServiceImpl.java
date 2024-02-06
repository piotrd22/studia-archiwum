package com.example.zad.services;

import com.example.zad.domain.Address;
import com.example.zad.domain.Restaurant;
import com.example.zad.dtos.request.RestaurantFilterDto;
import com.example.zad.exceptions.AlreadyExistsException;
import com.example.zad.exceptions.NotFoundException;
import com.example.zad.repositories.AddressRepository;
import com.example.zad.repositories.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, AddressRepository addressRepository) {
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Restaurant> searchRestaurants(RestaurantFilterDto filterDto, Pageable pageable) {
        return restaurantRepository.searchRestaurants(filterDto, pageable).getContent();
    }

    @Override
    public List<Restaurant> getRestaurantsBySupplierId(Long supplierId, Pageable pageable) {
        return restaurantRepository.findRestaurantsBySupplierId(supplierId, pageable).getContent();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        // This would be optional but thanks to orElseThrow I don't use it
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant not found with id: " + id));
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant updatedRestaurant) {
        return restaurantRepository.save(updatedRestaurant);
    }

    @Override
    @Transactional
    // I do not remove ratings or others to preserve history
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = getRestaurantById(id);

        if(restaurant.getAddress() != null) {
            addressRepository.delete(restaurant.getAddress());
            restaurant.setAddress(null);
        }

        restaurant.setDeleted(true);
        restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public Restaurant addAddressToRestaurant(Long id, Address address) {
        Restaurant restaurant = getRestaurantById(id);
        if (restaurant.getAddress() != null) {
            throw new AlreadyExistsException("Restaurant already have address");
        }
        address = addressRepository.save(address);
        restaurant.setAddress(address);
        return restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public Restaurant updateAddressInRestaurant(Long id, Address address) {
        Restaurant restaurant = getRestaurantById(id);
        address = addressRepository.save(address);
        restaurant.setAddress(address);
        return restaurantRepository.save(restaurant);
    }
}
