package com.example.zad.services;

import com.example.zad.domain.Rating;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RatingService {
    Rating getRating(Long id);
    Rating addRating(Rating rating, Long restaurantId);
    Rating updateRating(Rating updatedRating);
    void deleteRating(Long id);
    List<Rating> getRatingsByRestaurantId(Long restaurantId, Pageable pageable);
    Double getAverageRatingForRestaurant(Long restaurantId);
}
