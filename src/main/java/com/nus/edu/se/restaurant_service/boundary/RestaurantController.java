package com.nus.edu.se.restaurant_service.boundary;

import com.nus.edu.se.restaurant_service.model.Restaurants;
import com.nus.edu.se.restaurant_service.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("get/{restaurantName}")
    public ResponseEntity<Restaurants> getRestaurantByName(@PathVariable String restaurantName) {
        return restaurantService.getRestaurantByName(restaurantName);
    }

}
