package com.nus.edu.se.restaurant_service.dao;


import com.nus.edu.se.restaurant_service.model.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurants, UUID> {
    Restaurants findByRestaurantName(String restaurantName);
}
