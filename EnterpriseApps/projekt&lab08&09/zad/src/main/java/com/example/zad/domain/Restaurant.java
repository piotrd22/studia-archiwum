package com.example.zad.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate foundingDate;
    private Boolean isMichelinStarred = Boolean.FALSE;
    private Integer numberOfEmployees;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cuisine;

    private boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

//    @ManyToMany
//    private List<Supplier> suppliers;

    // I arrange M:M with two 1:M for this object, as you told me during the classes
    @OneToMany
    private List<AgreementRestaurantSupplier> agreementRestaurantSupplierList = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
