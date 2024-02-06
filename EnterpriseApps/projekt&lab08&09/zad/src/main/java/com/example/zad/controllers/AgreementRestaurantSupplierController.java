package com.example.zad.controllers;

import com.example.zad.domain.AgreementRestaurantSupplier;
import com.example.zad.dtos.request.AddAgreementDto;
import com.example.zad.dtos.response.AgreementRestaurantSupplierDto;
import com.example.zad.mappers.AgreementRestaurantSupplierMapper;
import com.example.zad.services.AgreementRestaurantSupplierService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/agreements")
public class AgreementRestaurantSupplierController extends AbstractControllerBase {

    private final AgreementRestaurantSupplierMapper agreementRestaurantSupplierMapper;
    private final AgreementRestaurantSupplierService agreementRestaurantSupplierService;

    public AgreementRestaurantSupplierController(AgreementRestaurantSupplierMapper agreementRestaurantSupplierMapper, AgreementRestaurantSupplierService agreementRestaurantSupplierService) {
        this.agreementRestaurantSupplierMapper = agreementRestaurantSupplierMapper;
        this.agreementRestaurantSupplierService = agreementRestaurantSupplierService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementRestaurantSupplierDto> getAgreement(@PathVariable Long id) {
        logger.info("Inside: AgreementRestaurantSupplierController -> getAgreement()...");
        AgreementRestaurantSupplier agreement = agreementRestaurantSupplierService.getAgreement(id);
        return ResponseEntity.ok().body(agreementRestaurantSupplierMapper.agreementRestaurantSupplierToAgreementRestaurantSupplierDto(agreement));
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<AgreementRestaurantSupplierDto>> getAgreementsByRestaurantId(@PathVariable Long id, Pageable pageable) {
        logger.info("Inside: AgreementRestaurantSupplierController -> getAgreementsByRestaurantId()...");
        List<AgreementRestaurantSupplier> agreements = agreementRestaurantSupplierService.getAgreementsByRestaurantId(id, pageable);
        return ResponseEntity.ok().body(agreements.stream().map(agreementRestaurantSupplierMapper::agreementRestaurantSupplierToAgreementRestaurantSupplierDto).toList());
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<List<AgreementRestaurantSupplierDto>> getAgreementsBySupplierId(@PathVariable Long id, Pageable pageable) {
        logger.info("Inside: AgreementRestaurantSupplierController -> getAgreementsBySupplierId()...");
        List<AgreementRestaurantSupplier> agreements = agreementRestaurantSupplierService.getAgreementsBySupplierId(id, pageable);
        return ResponseEntity.ok().body(agreements.stream().map(agreementRestaurantSupplierMapper::agreementRestaurantSupplierToAgreementRestaurantSupplierDto).toList());
    }

    @PostMapping
    public ResponseEntity<AgreementRestaurantSupplierDto> addAgreement(@RequestBody @Valid AddAgreementDto dto, HttpServletRequest request) {
        logger.info("Inside: AgreementRestaurantSupplierController -> addAgreement()...");
        AgreementRestaurantSupplier agreement = agreementRestaurantSupplierMapper.addAgreementDtoToAgreementRestaurantSupplier(dto);
        agreement = agreementRestaurantSupplierService.addAgreement(agreement);
        URI location = getURILocationFromRequest(agreement.getId(), request);
        return ResponseEntity.created(location).body(agreementRestaurantSupplierMapper.agreementRestaurantSupplierToAgreementRestaurantSupplierDto(agreement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgreement(@PathVariable Long id) {
        logger.info("Inside: AgreementRestaurantSupplierController -> deleteAgreement()...");
        agreementRestaurantSupplierService.deleteAgreement(id);
        return ResponseEntity.noContent().build();
    }
}
