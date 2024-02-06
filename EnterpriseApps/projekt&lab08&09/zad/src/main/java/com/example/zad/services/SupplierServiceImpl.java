package com.example.zad.services;

import com.example.zad.domain.Supplier;
import com.example.zad.exceptions.AlreadyExistsException;
import com.example.zad.exceptions.NotFoundException;
import com.example.zad.repositories.SupplierRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    @Override
    public List<Supplier> getSuppliers(Pageable pageable) {
        return supplierRepository.findAll(pageable).getContent();
    }

    @Override
    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        if (supplierRepository.existsByNameIgnoreCase(supplier.getName())) {
            throw new AlreadyExistsException("Supplier with this name already exists");
        }

        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplier(id);
        supplier.setDeleted(true);
        supplierRepository.save(supplier);
    }
}
