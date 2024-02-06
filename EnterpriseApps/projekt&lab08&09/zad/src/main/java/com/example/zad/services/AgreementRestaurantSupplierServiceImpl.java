package com.example.zad.services;

import com.example.zad.domain.AgreementRestaurantSupplier;
import com.example.zad.domain.Restaurant;
import com.example.zad.domain.Supplier;
import com.example.zad.exceptions.AlreadyExistsException;
import com.example.zad.exceptions.NotFoundException;
import com.example.zad.repositories.AgreementRestaurantSupplierRepository;
import com.example.zad.repositories.RestaurantRepository;
import com.example.zad.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgreementRestaurantSupplierServiceImpl implements AgreementRestaurantSupplierService {

    private final AgreementRestaurantSupplierRepository agreementRestaurantSupplierRepository;
    private final RestaurantRepository restaurantRepository;
    private final SupplierRepository supplierRepository;

    public AgreementRestaurantSupplierServiceImpl(AgreementRestaurantSupplierRepository agreementRestaurantSupplierRepository, RestaurantRepository restaurantRepository, SupplierRepository supplierRepository) {
        this.agreementRestaurantSupplierRepository = agreementRestaurantSupplierRepository;
        this.restaurantRepository = restaurantRepository;
        this.supplierRepository = supplierRepository;
    }


    @Override
    public AgreementRestaurantSupplier getAgreement(Long id) {
        return agreementRestaurantSupplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Agreement not found"));
    }

    @Override
    public List<AgreementRestaurantSupplier> getAgreementsByRestaurantId(Long restaurantId, Pageable pageable) {
        return agreementRestaurantSupplierRepository.findAgreementRestaurantSuppliersByRestaurantId(restaurantId, pageable).getContent();
    }

    @Override
    public List<AgreementRestaurantSupplier> getAgreementsBySupplierId(Long supplierId, Pageable pageable) {
        return agreementRestaurantSupplierRepository.findAgreementRestaurantSuppliersBySupplierId(supplierId, pageable).getContent();
    }

    @Override
    @Transactional
    public AgreementRestaurantSupplier addAgreement(AgreementRestaurantSupplier agreement) {
        Restaurant restaurant = agreement.getRestaurant();
        Supplier supplier = agreement.getSupplier();

        if (agreementRestaurantSupplierRepository.existsByRestaurantIdAndSupplierId(restaurant.getId(), supplier.getId())) {
            throw new AlreadyExistsException("Agreement between this supplier and restaurant already exists");
        }

        agreement.setAgreementDate(LocalDate.now());
        agreement = agreementRestaurantSupplierRepository.save(agreement);
        supplier.getAgreementRestaurantSupplierList().add(agreement);
        restaurant.getAgreementRestaurantSupplierList().add(agreement);
        restaurantRepository.save(restaurant);
        supplierRepository.save(supplier);

        return agreement;
    }

    @Override
    public void deleteAgreement(Long id) {
        AgreementRestaurantSupplier agreement = getAgreement(id);
        agreement.setDeleted(true);
        agreementRestaurantSupplierRepository.save(agreement);
    }
}
