package com.example.zad.repositories;

import com.example.zad.domain.AgreementRestaurantSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementRestaurantSupplierRepository extends JpaRepository<AgreementRestaurantSupplier, Long> {
    @Query("SELECT r FROM AgreementRestaurantSupplier r WHERE r.isDeleted = false")
    Page<AgreementRestaurantSupplier> findAll(Pageable pageable);

    @Query("SELECT r FROM AgreementRestaurantSupplier r WHERE r.id = :id AND r.isDeleted = false")
    Optional<AgreementRestaurantSupplier> findById(Long id);

    Boolean existsByRestaurantIdAndSupplierId(Long restaurantId, Long supplierId);

    Page<AgreementRestaurantSupplier> findAgreementRestaurantSuppliersByRestaurantId(Long restaurantId, Pageable pageable);

    Page<AgreementRestaurantSupplier> findAgreementRestaurantSuppliersBySupplierId(Long supplierId, Pageable pageable);
}
