package com.example.zad.config;

import com.example.zad.domain.Restaurant;
import com.example.zad.dto.CreateRestaurantRequestDto;
import com.example.zad.dto.RestaurantDto;
import com.example.zad.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class RestaurantCreator {
    @Bean
    public CommandLineRunner dbSetup(@Autowired RestaurantService service) {
        return args -> {
            System.out.println("CommandLineRunner started...");

            for (int i = 0; i < 10; i++) {
                String name = "Restaurant " + (i + 1);
                String city = "City " + (i + 1);
                String cuisine = "Cuisine " + (i + 1);

                CreateRestaurantRequestDto restaurant = new CreateRestaurantRequestDto(name, city, cuisine);
                service.createRestaurant(restaurant);
            }

            System.out.println("CommandLineRunner stopped...");
        };
    }
}
