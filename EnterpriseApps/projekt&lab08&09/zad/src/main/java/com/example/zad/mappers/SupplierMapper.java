package com.example.zad.mappers;

import com.example.zad.domain.Supplier;
import com.example.zad.dtos.request.AddSupplierDto;
import com.example.zad.dtos.response.SupplierDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDto supplierToSupplierDto(Supplier supplier);

    Supplier addSupplierDtoToSupplier(AddSupplierDto addSupplierDto);
}
