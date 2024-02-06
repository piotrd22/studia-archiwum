package com.example.zad.controllers;

import com.example.zad.domain.Rating;
import com.example.zad.dtos.request.AddRatingDto;
import com.example.zad.dtos.request.UpdateRatingDto;
import com.example.zad.dtos.response.RatingDto;
import com.example.zad.mappers.RatingMapper;
import com.example.zad.services.RatingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController extends AbstractControllerBase {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    public RatingController(RatingService ratingService, RatingMapper ratingMapper) {
        this.ratingService = ratingService;
        this.ratingMapper = ratingMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDto> getRating(@PathVariable Long id) {
        logger.info("Inside: RatingController -> getRating()...");
        Rating rating = ratingService.getRating(id);
        return ResponseEntity.ok(ratingMapper.ratingToRatingDto(rating));
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<RatingDto> addRating(@RequestBody @Valid AddRatingDto dto, @PathVariable Long restaurantId, HttpServletRequest request) {
        logger.info("Inside: RatingController -> addRating()...");
        Rating rating = ratingMapper.addRatingDtoToRating(dto);
        rating = ratingService.addRating(rating, restaurantId);
        URI location = getURILocationFromRequest(rating.getId(), request);
        return ResponseEntity.created(location).body(ratingMapper.ratingToRatingDto(rating));
    }

    @GetMapping("restaurant/{restaurantId}")
    public ResponseEntity<List<RatingDto>> getRatingsByRestaurantId(@PathVariable Long restaurantId, Pageable pageable) {
        logger.info("Inside: RatingController -> getRatingsByRestaurantId()...");
        List<Rating> ratings = ratingService.getRatingsByRestaurantId(restaurantId, pageable);
        return ResponseEntity.ok(ratings.stream().map(ratingMapper::ratingToRatingDto).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDto> updateRating(@PathVariable Long id, @RequestBody @Valid UpdateRatingDto dto) {
        logger.info("Inside: RatingController -> updateRating()...");
        Rating rating = ratingService.getRating(id);
        ratingMapper.updateRating(rating, dto);
        rating = ratingService.updateRating(rating);
        return ResponseEntity.ok(ratingMapper.ratingToRatingDto(rating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        logger.info("Inside: RatingController -> deleteRating()...");
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurant/score/{restaurantId}")
    public ResponseEntity<Double> getAverageRatingForRestaurant(@PathVariable Long restaurantId) {
        logger.info("Inside: RatingController -> getAverageRatingForRestaurant()...");
        Double rating = ratingService.getAverageRatingForRestaurant(restaurantId);
        return ResponseEntity.ok(rating);
    }
}
