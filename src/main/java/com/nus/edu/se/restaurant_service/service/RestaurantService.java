package com.nus.edu.se.restaurant_service.service;

import com.nus.edu.se.restaurant_service.dao.RestaurantRepository;
import com.nus.edu.se.restaurant_service.model.Restaurants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public ResponseEntity<Restaurants> getRestaurantByName(String restaurantName) {
        try {
            return new ResponseEntity<>(restaurantRepository.findByRestaurantName(restaurantName), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Restaurants(), HttpStatus.BAD_REQUEST);
    }

}
