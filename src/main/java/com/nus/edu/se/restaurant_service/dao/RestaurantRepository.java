package com.nus.edu.se.restaurant_service.dao;


import com.nus.edu.se.restaurant_service.model.Restaurants;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurants, String> {
    Restaurants findByRestaurantName(String restaurantName);
}
