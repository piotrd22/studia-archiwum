package com.example.zad.config;

import com.example.zad.dto.CreateRestaurantRequestDto;
import com.example.zad.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantCreator {
    @Bean
    public CommandLineRunner setupDbWithInitialRestaurants(@Autowired RestaurantService service) {
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
