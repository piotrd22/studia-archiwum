package com.example.zad.mappers;

import com.example.zad.domain.Restaurant;
import com.example.zad.dtos.request.AddRestaurantDto;
import com.example.zad.dtos.request.UpdateRestaurantDto;
import com.example.zad.dtos.response.RestaurantDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {AgreementRestaurantSupplierMapper.class})
public abstract class RestaurantMapper {

    @Autowired
    private Updater updater;

    public abstract RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);

    public abstract Restaurant addRestaurantDtoToRestaurant(AddRestaurantDto dto);

    public abstract Restaurant updateRestaurantDtoToRestaurant(UpdateRestaurantDto dto);

    public void updateRestaurant(Restaurant restaurant, UpdateRestaurantDto dto) {
        updater.update(restaurant, updateRestaurantDtoToRestaurant(dto));
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
        void update(@MappingTarget Restaurant restaurant, Restaurant dto);
    }
}
