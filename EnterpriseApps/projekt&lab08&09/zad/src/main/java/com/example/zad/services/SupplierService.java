package com.example.zad.services;

import com.example.zad.domain.Supplier;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplierService {
    List<Supplier> getSuppliers(Pageable pageable);
    Supplier getSupplier(Long id);
    Supplier addSupplier(Supplier supplier);
    void deleteSupplier(Long id);
}
