package com.example.zad.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private Long id;
    private LocalDate foundingDate;
    private Boolean isMichelinStarred;
    private Integer numberOfEmployees;
    private String name;
    private String cuisine;
    private boolean isDeleted;
    private AddressDto address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
