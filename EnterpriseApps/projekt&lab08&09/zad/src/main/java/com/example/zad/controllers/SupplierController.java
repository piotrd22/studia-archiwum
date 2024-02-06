package com.example.zad.controllers;

import com.example.zad.domain.Supplier;
import com.example.zad.dtos.request.AddSupplierDto;
import com.example.zad.dtos.response.SupplierDto;
import com.example.zad.mappers.SupplierMapper;
import com.example.zad.services.SupplierService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController extends AbstractControllerBase {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    public SupplierController(SupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getSuppliers(Pageable pageable) {
        logger.info("Inside: SupplierController -> getSuppliers()...");
        List<Supplier> suppliers = supplierService.getSuppliers(pageable);
        return ResponseEntity.ok().body(suppliers.stream().map(supplierMapper::supplierToSupplierDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSuppliers(@PathVariable Long id) {
        logger.info("Inside: SupplierController -> getSupplier()...");
        Supplier supplier = supplierService.getSupplier(id);
        return ResponseEntity.ok().body(supplierMapper.supplierToSupplierDto(supplier));
    }

    @PostMapping
    public ResponseEntity<SupplierDto> addSupplier(@RequestBody @Valid AddSupplierDto dto, HttpServletRequest request) {
        logger.info("Inside: SupplierController -> addSupplier()...");
        Supplier supplier = supplierMapper.addSupplierDtoToSupplier(dto);
        supplier = supplierService.addSupplier(supplier);
        URI location = getURILocationFromRequest(supplier.getId(), request);
        return ResponseEntity.created(location).body(supplierMapper.supplierToSupplierDto(supplier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        logger.info("Inside: SupplierController -> deleteSupplier()...");
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
