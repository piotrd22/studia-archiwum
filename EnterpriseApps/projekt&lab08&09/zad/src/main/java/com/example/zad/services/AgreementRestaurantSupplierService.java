package com.example.zad.services;

import com.example.zad.domain.AgreementRestaurantSupplier;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AgreementRestaurantSupplierService {
    AgreementRestaurantSupplier getAgreement(Long id);
    List<AgreementRestaurantSupplier> getAgreementsByRestaurantId(Long restaurantId, Pageable pageable);
    List<AgreementRestaurantSupplier> getAgreementsBySupplierId(Long supplierId, Pageable pageable);
    AgreementRestaurantSupplier addAgreement(AgreementRestaurantSupplier agreement);
    void deleteAgreement(Long id);
}
