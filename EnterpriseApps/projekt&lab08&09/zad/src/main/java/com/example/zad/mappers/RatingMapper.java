package com.example.zad.mappers;

import com.example.zad.domain.Rating;
import com.example.zad.domain.Restaurant;
import com.example.zad.dtos.request.AddRatingDto;
import com.example.zad.dtos.request.UpdateRatingDto;
import com.example.zad.dtos.response.RatingDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class RatingMapper {

    @Autowired
    private Updater updater;

    public abstract Rating addRatingDtoToRating(AddRatingDto addRatingDto);

    @Mapping(target = "restaurantId", source = "restaurant", qualifiedByName = "restaurantToRestaurantId")
    @Mapping(target = "restaurantName", source = "restaurant", qualifiedByName = "restaurantToRestaurantName")
    public abstract RatingDto ratingToRatingDto(Rating rating);

    @Named("restaurantToRestaurantId")
    public Long restaurantToRestaurantId(Restaurant restaurant) {
        return restaurant.getId();
    }

    @Named("restaurantToRestaurantName")
    public String restaurantToRestaurantName(Restaurant restaurant) {
        return restaurant.getName();
    }

    public abstract Rating updateRatingDtoToRating(UpdateRatingDto updateRatingDto);

    public void updateRating(Rating rating, UpdateRatingDto dto) {
        updater.update(rating, updateRatingDtoToRating(dto));
    }

    @Mapper(
            componentModel = "spring",
            unmappedSourcePolicy = ReportingPolicy.ERROR,
            unmappedTargetPolicy = ReportingPolicy.IGNORE,
            typeConversionPolicy = ReportingPolicy.WARN,
            collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    interface Updater {
        void update(@MappingTarget Rating rating, Rating dto);
    }
}
