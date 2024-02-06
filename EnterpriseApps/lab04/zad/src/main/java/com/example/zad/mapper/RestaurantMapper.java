package com.example.zad.mapper;

import com.example.zad.domain.Restaurant;
import com.example.zad.dto.CreateRestaurantRequestDto;
import com.example.zad.dto.RestaurantDto;
import com.example.zad.dto.UpdateRestaurantRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);

    @Mapping(target = "id", ignore = true)
    Restaurant createRestaurantRequestDtoToRestaurant(CreateRestaurantRequestDto dto);

    Restaurant updateRestaurantRequestDtoToRestaurant(UpdateRestaurantRequestDto dto, @MappingTarget Restaurant restaurant);
}
