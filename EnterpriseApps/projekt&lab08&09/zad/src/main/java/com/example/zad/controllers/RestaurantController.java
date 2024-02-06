package com.example.zad.controllers;

import com.example.zad.domain.Address;
import com.example.zad.domain.Restaurant;
import com.example.zad.dtos.request.*;
import com.example.zad.dtos.response.RestaurantDto;
import com.example.zad.mappers.AddressMapper;
import com.example.zad.mappers.RestaurantMapper;
import com.example.zad.services.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController extends AbstractControllerBase {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final AddressMapper addressMapper;

    public RestaurantController(RestaurantService restaurantService, RestaurantMapper restaurantMapper, AddressMapper addressMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
        this.addressMapper = addressMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long id) {
        logger.info("Inside: RestaurantController -> getRestaurantById()...");
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurantMapper.restaurantToRestaurantDto(restaurant));
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsBySupplierId(@PathVariable Long id, Pageable pageable) {
        logger.info("Inside: RestaurantController -> getRestaurantsBySupplierId()...");
        List<Restaurant> restaurants = restaurantService.getRestaurantsBySupplierId(id, pageable);
        return ResponseEntity.ok(restaurants.stream().map(restaurantMapper::restaurantToRestaurantDto).toList());
    }

    @PostMapping("/search")
    public ResponseEntity<List<RestaurantDto>> searchRestaurants(@RequestBody @Valid RestaurantFilterDto dto, Pageable pageable) {
        logger.info("Inside: RestaurantController -> searchRestaurants()...");
        List<Restaurant> restaurants = restaurantService.searchRestaurants(dto, pageable);
        return ResponseEntity.ok(restaurants.stream().map(restaurantMapper::restaurantToRestaurantDto).toList());
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody @Valid AddRestaurantDto dto, HttpServletRequest request) {
        logger.info("Inside: RestaurantController -> addRestaurant()...");
        Restaurant restaurant = restaurantMapper.addRestaurantDtoToRestaurant(dto);
        restaurant = restaurantService.addRestaurant(restaurant);
        URI location = getURILocationFromRequest(restaurant.getId(), request);
        return ResponseEntity.created(location).body(restaurantMapper.restaurantToRestaurantDto(restaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        logger.info("Inside: RestaurantController -> deleteRestaurant()...");
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody @Valid UpdateRestaurantDto dto, @PathVariable Long id) {
        logger.info("Inside: RestaurantController -> updateRestaurant()...");
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        restaurantMapper.updateRestaurant(restaurant, dto);
        restaurant = restaurantService.updateRestaurant(restaurant);
        return ResponseEntity.ok(restaurantMapper.restaurantToRestaurantDto(restaurant));
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<RestaurantDto> addAddressToRestaurant(@PathVariable Long id, @RequestBody @Valid AddAddressToRestaurantDto dto) {
        logger.info("Inside: RestaurantController -> addAddressToRestaurant()...");
        Address address = addressMapper.addAddressToRestaurantDtoToAddress(dto);
        Restaurant restaurant = restaurantService.addAddressToRestaurant(id, address);
        return ResponseEntity.ok(restaurantMapper.restaurantToRestaurantDto(restaurant));
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<RestaurantDto> updateAddressInRestaurant(@PathVariable Long id, @RequestBody @Valid UpdateAddressInRestaurantDto dto) {
        logger.info("Inside: RestaurantController -> updateAddressInRestaurant()...");
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        Address address = restaurant.getAddress();
        addressMapper.updateAddress(address, dto);
        restaurant = restaurantService.updateAddressInRestaurant(id, address);
        return ResponseEntity.ok(restaurantMapper.restaurantToRestaurantDto(restaurant));
    }
}
