package com.example.zad.services;

import com.example.zad.domain.Rating;
import com.example.zad.domain.Restaurant;
import com.example.zad.exceptions.NotFoundException;
import com.example.zad.repositories.RatingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RestaurantService restaurantService;

    public RatingServiceImpl(RatingRepository ratingRepository, RestaurantService restaurantService) {
        this.ratingRepository = ratingRepository;
        this.restaurantService = restaurantService;
    }

    @Override
    public Rating getRating(Long id) {
        return ratingRepository.findById(id).orElseThrow(() -> new NotFoundException("Rating not found"));
    }

    @Override
    public Rating addRating(Rating rating, Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        rating.setRestaurant(restaurant);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Rating updatedRating) {
        return ratingRepository.save(updatedRating);
    }

    @Override
    public void deleteRating(Long id) {
        Rating rating = getRating(id);
        ratingRepository.delete(rating);
    }

    @Override
    public List<Rating> getRatingsByRestaurantId(Long restaurantId, Pageable pageable) {
        return ratingRepository.getRatingsByRestaurantId(restaurantId, pageable).getContent();
    }

    @Override
    public Double getAverageRatingForRestaurant(Long restaurantId) {
        return ratingRepository.getAverageRatingForRestaurant(restaurantId);
    }
}
