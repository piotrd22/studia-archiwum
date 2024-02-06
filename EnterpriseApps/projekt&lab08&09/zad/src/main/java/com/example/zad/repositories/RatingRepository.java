package com.example.zad.repositories;

import com.example.zad.domain.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Page<Rating> getRatingsByRestaurantId(Long restaurantId, Pageable pageable);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.restaurant.id = :restaurantId")
    Double getAverageRatingForRestaurant(@Param("restaurantId") Long restaurantId);
}
