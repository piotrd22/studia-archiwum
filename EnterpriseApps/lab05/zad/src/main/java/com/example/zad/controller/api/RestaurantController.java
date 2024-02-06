package com.example.zad.controller.api;

import com.example.zad.dto.CreateRestaurantRequestDto;
import com.example.zad.dto.RestaurantDto;
import com.example.zad.dto.UpdateRestaurantRequestDto;
import com.example.zad.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> restaurants = service.getAllRestaurants();
        return ResponseEntity.ok().body(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable UUID id) {
        RestaurantDto restaurant = service.getRestaurantById(id);
        return ResponseEntity.ok().body(restaurant);
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@Valid @RequestBody CreateRestaurantRequestDto dto) {
        RestaurantDto createdRestaurant = service.createRestaurant(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdRestaurant.id())
                .toUri();

        return ResponseEntity.created(location).body(createdRestaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(
            @Valid @RequestBody UpdateRestaurantRequestDto dto,
            @PathVariable UUID id
    ) {
        RestaurantDto updatedRestaurant = service.updateRestaurant(dto, id);

        return ResponseEntity.ok().body(updatedRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
        service.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
