package com.example.zad.config;

import com.example.zad.domain.*;
import com.example.zad.services.AgreementRestaurantSupplierService;
import com.example.zad.services.RatingService;
import com.example.zad.services.RestaurantService;
import com.example.zad.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner setUpApp(
            @Autowired RestaurantService restaurantService,
            @Autowired RatingService ratingService,
            @Autowired SupplierService supplierService,
            @Autowired AgreementRestaurantSupplierService agreementRestaurantSupplierService
            ) {
        return args -> {
            for (int i = 0; i < 4; i++) {
                Restaurant restaurant = new Restaurant();
                restaurant.setFoundingDate(LocalDate.of(2002, Month.JANUARY, 10));
                restaurant.setIsMichelinStarred(Boolean.TRUE);
                restaurant.setNumberOfEmployees(10);
                restaurant.setName("Restauracja " + (i + 1));
                restaurant.setCuisine("Kuchnia " + (i + 1));

                restaurant = restaurantService.addRestaurant(restaurant);

                Address address = new Address();
                address.setStreet("Ulica " + (i + 1));
                address.setCity("Miasto " + (i + 1));
                address.setState("Stan " + (i + 1));
                address.setZipCode("Kod pocztowy " + (i + 1));

                restaurantService.addAddressToRestaurant(restaurant.getId(), address);

                Rating rating = new Rating();
                rating.setScore(10D);
                rating.setReview("Wybitne dzieło!");
                ratingService.addRating(rating, restaurant.getId());

                Supplier supplier = new Supplier();
                supplier.setName("Dostawca" + (i + 1));
                supplierService.addSupplier(supplier);

                AgreementRestaurantSupplier agreement = new AgreementRestaurantSupplier();
                agreement.setRestaurant(restaurant);
                agreement.setSupplier(supplier);
                agreementRestaurantSupplierService.addAgreement(agreement);
            }
        };
    }
}
