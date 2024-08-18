package com.nus.edu.se.restaurant_service.dao;


import com.nus.edu.se.restaurant_service.model.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurants, Integer> {
    Restaurants findByRestaurantName(String restaurantName);
}
