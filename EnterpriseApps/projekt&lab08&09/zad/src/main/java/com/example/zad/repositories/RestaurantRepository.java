package com.example.zad.repositories;

import com.example.zad.domain.Restaurant;
import com.example.zad.dtos.request.RestaurantFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> { // I use JpaRepository because it inherits PagingAndSortingRepository and CrudRepository
    @Query("SELECT r FROM Restaurant r WHERE r.isDeleted = false " +
            "AND (:#{#filterDto.minRating} IS NULL OR " +
            "     (SELECT AVG(rating.score) FROM Rating rating WHERE rating.restaurant = r) >= :#{#filterDto.minRating}) " +
            "AND (:#{#filterDto.name} IS NULL OR LOWER(r.name) LIKE LOWER(concat('%', :#{#filterDto.name}, '%'))) " +
            "AND (:#{#filterDto.isMichelinStarred} IS NULL OR r.isMichelinStarred = :#{#filterDto.isMichelinStarred}) " +
            "AND (:#{#filterDto.minFoundingDate} IS NULL OR r.foundingDate >= :#{#filterDto.minFoundingDate}) " +
            "AND (:#{#filterDto.cuisine} IS NULL OR LOWER(r.cuisine) LIKE LOWER(concat('%', :#{#filterDto.cuisine}, '%'))) " +
            "AND (:#{#filterDto.city} IS NULL OR LOWER(r.address.city) LIKE LOWER(concat('%', :#{#filterDto.city}, '%'))) " +
            "AND (:#{#filterDto.state} IS NULL OR LOWER(r.address.state) LIKE LOWER(concat('%', :#{#filterDto.state}, '%'))) " +
            "AND (:#{#filterDto.zipCode} IS NULL OR LOWER(r.address.zipCode) LIKE LOWER(concat('%', :#{#filterDto.zipCode}, '%'))) " +
            "AND (:#{#filterDto.street} IS NULL OR LOWER(r.address.street) LIKE LOWER(concat('%', :#{#filterDto.street}, '%')))")
    Page<Restaurant> searchRestaurants(@Param("filterDto") RestaurantFilterDto filterDto, Pageable pageable);

    // Only for learning purposes -- native query
    @Query(value = "SELECT * FROM restaurant WHERE id = :id AND is_deleted = false", nativeQuery = true)
    Optional<Restaurant> findById(@Param("id") Long id);

    @Query("SELECT r FROM Restaurant r " +
            "JOIN r.agreementRestaurantSupplierList ars " +
            "JOIN ars.supplier s " +
            "WHERE r.isDeleted = false " +
            "AND s.isDeleted = false " +
            "AND ars.isDeleted = false " +
            "AND s.id = :supplierId")
    Page<Restaurant> findRestaurantsBySupplierId(@Param("supplierId") Long supplierId, Pageable pageable);
}
