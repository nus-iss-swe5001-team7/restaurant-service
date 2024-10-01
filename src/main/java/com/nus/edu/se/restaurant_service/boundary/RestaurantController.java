package com.nus.edu.se.restaurant_service.boundary;

import com.nus.edu.se.restaurant_service.model.Restaurants;
import com.nus.edu.se.restaurant_service.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("get/{restaurantName}")
    public ResponseEntity<Restaurants> getRestaurantByName(@PathVariable String restaurantName) {
        return restaurantService.getRestaurantByName(restaurantName);
    }

    @GetMapping("getRestaurantById/{id}")
    public ResponseEntity<Restaurants> getRestaurantById(HttpServletRequest request, @PathVariable String id) throws AuthenticationException {
        String token = restaurantService.resolveToken(request);
        return restaurantService.getRestaurantById(id, token);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Restaurants>> getAllRestaurants(HttpServletRequest request) throws AuthenticationException {
        String token = restaurantService.resolveToken(request);
        return restaurantService.getAllRestaurants(token);
    }

}
