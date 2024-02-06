package com.example.zad.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantFilterDto {
    private String name;
    private Boolean isMichelinStarred;
    private LocalDate minFoundingDate;
    private Double minRating;
    private String cuisine;
    private String city;
    private String state;
    private String zipCode;
    private String street;
}
