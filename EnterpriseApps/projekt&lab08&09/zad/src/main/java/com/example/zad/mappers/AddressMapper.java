package com.example.zad.mappers;

import com.example.zad.domain.Address;
import com.example.zad.dtos.request.AddAddressToRestaurantDto;
import com.example.zad.dtos.request.UpdateAddressInRestaurantDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {

    @Autowired
    private Updater updater;

    public abstract Address addAddressToRestaurantDtoToAddress(AddAddressToRestaurantDto dto);

    public abstract Address updateAddressInRestaurantDtoToAddress(UpdateAddressInRestaurantDto dto);

    public void updateAddress(Address address, UpdateAddressInRestaurantDto dto) {
        updater.update(address, updateAddressInRestaurantDtoToAddress(dto));
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
        void update(@MappingTarget Address address, Address dto);
    }
}
