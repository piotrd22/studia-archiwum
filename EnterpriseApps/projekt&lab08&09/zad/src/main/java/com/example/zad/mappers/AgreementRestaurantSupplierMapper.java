package com.example.zad.mappers;

import com.example.zad.domain.AgreementRestaurantSupplier;
import com.example.zad.domain.Restaurant;
import com.example.zad.domain.Supplier;
import com.example.zad.dtos.request.AddAgreementDto;
import com.example.zad.dtos.response.AgreementRestaurantSupplierDto;
import com.example.zad.services.RestaurantService;
import com.example.zad.services.SupplierService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AgreementRestaurantSupplierMapper {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private SupplierService supplierService;

    public abstract AgreementRestaurantSupplierDto agreementRestaurantSupplierToAgreementRestaurantSupplierDto(AgreementRestaurantSupplier agreementRestaurantSupplier);

    @Mapping(source = "restaurantId", target = "restaurant", qualifiedByName = "restaurantIdToRestaurant")
    @Mapping(source = "supplierId", target = "supplier", qualifiedByName = "supplierIdToSupplier")
    public abstract AgreementRestaurantSupplier addAgreementDtoToAgreementRestaurantSupplier(AddAgreementDto addAgreementDto);

    @Named("restaurantIdToRestaurant")
    public Restaurant restaurantIdToRestaurant(Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @Named("supplierIdToSupplier")
    public Supplier supplierIdToSupplier(Long id) {
        return supplierService.getSupplier(id);
    }
}
