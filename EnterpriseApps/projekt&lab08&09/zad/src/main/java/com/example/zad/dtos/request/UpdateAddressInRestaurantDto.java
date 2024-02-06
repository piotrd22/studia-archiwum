package com.example.zad.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressInRestaurantDto {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
