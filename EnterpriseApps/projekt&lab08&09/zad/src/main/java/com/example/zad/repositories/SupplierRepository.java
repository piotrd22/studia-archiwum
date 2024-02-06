package com.example.zad.repositories;

import com.example.zad.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT r FROM Supplier r WHERE r.isDeleted = false")
    Page<Supplier> findAll(Pageable pageable);

    @Query("SELECT r FROM Supplier r WHERE r.id = :id AND r.isDeleted = false")
    Optional<Supplier> findById(Long id);

    Boolean existsByNameIgnoreCase(String name);
}
