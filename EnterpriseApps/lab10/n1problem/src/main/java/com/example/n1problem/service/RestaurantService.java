package com.example.n1problem.service;

import com.example.n1problem.domain.Rating;
import com.example.n1problem.domain.Restaurant;
import com.example.n1problem.repository.RatingRepository;
import com.example.n1problem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RatingRepository ratingRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, RatingRepository ratingRepository) {
        this.restaurantRepository = restaurantRepository;
        this.ratingRepository = ratingRepository;
    }

    public void fillData() {
        Restaurant restaurant1 = Restaurant.builder()
                .name("Res1")
                .build();
        Restaurant restaurant2 = Restaurant.builder()
                .name("Res2")
                .build();
        Restaurant restaurant3 = Restaurant.builder()
                .name("Res3")
                .build();
        Restaurant restaurant4 = Restaurant.builder()
                .name("Res4")
                .build();
        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);
        restaurantRepository.save(restaurant3);
        restaurantRepository.save(restaurant4);

        Rating rating1 = Rating.builder()
                .score(1.0)
                .restaurant(restaurant1)
                .build();
        Rating rating2 = Rating.builder()
                .score(2.0)
                .restaurant(restaurant1)
                .build();
        Rating rating3 = Rating.builder()
                .score(3.0)
                .restaurant(restaurant2)
                .build();
        Rating rating4 = Rating.builder()
                .score(4.0)
                .restaurant(restaurant2)
                .build();
        Rating rating5 = Rating.builder()
                .score(5.0)
                .restaurant(restaurant3)
                .build();
        Rating rating6 = Rating.builder()
                .score(6.0)
                .restaurant(restaurant3)
                .build();
        Rating rating7 = Rating.builder()
                .score(7.0)
                .restaurant(restaurant4)
                .build();
        Rating rating8 = Rating.builder()
                .score(8.0)
                .restaurant(restaurant4)
                .build();
        ratingRepository.save(rating1);
        ratingRepository.save(rating2);
        ratingRepository.save(rating3);
        ratingRepository.save(rating4);
        ratingRepository.save(rating5);
        ratingRepository.save(rating6);
        ratingRepository.save(rating7);
        ratingRepository.save(rating8);
    }

    public void learning() {
        for (Rating rating : ratingRepository.findAll()) {
            System.out.println(rating);
        }
    }
}
