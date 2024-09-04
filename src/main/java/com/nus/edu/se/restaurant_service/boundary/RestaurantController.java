package com.nus.edu.se.restaurant_service.boundary;

import com.nus.edu.se.restaurant_service.dao.RestaurantRepository;
import com.nus.edu.se.restaurant_service.model.Restaurants;
import com.nus.edu.se.restaurant_service.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @GetMapping("get/{restaurantName}")
    public ResponseEntity<Restaurants> getRestaurantByName(@PathVariable String restaurantName) {
        return restaurantService.getRestaurantByName(restaurantName);
    }

    @GetMapping("getRestaurantById/{id}")
    public ResponseEntity<Restaurants> getRestaurantById(@PathVariable UUID id) {
        ResponseEntity<Restaurants> restaurants = restaurantService.getRestaurantById(id);
        return restaurants;
    }

    @GetMapping(value = "/restaurants")
    public  List<Restaurants> getRestaurants() {
        List<Restaurants> restaurants =   restaurantRepository.findAll();
        return restaurants;
    }
}
