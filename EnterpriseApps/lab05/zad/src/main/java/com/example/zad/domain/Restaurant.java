package com.example.zad.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private UUID id;
    private String name;
    private String city;
    private String cuisine;
    private boolean hasMichelinStar;
    private int numberOfEmployees;
    private LocalDate dateFounded;
}
