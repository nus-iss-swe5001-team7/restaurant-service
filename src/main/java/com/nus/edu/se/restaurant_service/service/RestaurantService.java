package com.nus.edu.se.restaurant_service.service;

import com.nus.edu.se.restaurant_service.dao.RestaurantRepository;
import com.nus.edu.se.restaurant_service.model.Restaurants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    JwtTokenInterface jwtTokenInterface;

    public ResponseEntity<Restaurants> getRestaurantByName(String restaurantName) {
        try {
            return new ResponseEntity<>(restaurantRepository.findByRestaurantName(restaurantName), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Restaurants(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Restaurants> retrieveRestaurantById(String id) {
        try {
            Optional<Restaurants> restaurants = restaurantRepository.findById(id);
            return restaurants.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Restaurants(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Restaurants> getRestaurantById(String id, String token) throws AuthenticationException {
            if (Boolean.TRUE.equals(jwtTokenInterface.validateToken(token).getBody())) {
                return retrieveRestaurantById(id);
            } else {
                throw new AuthenticationException("User is not authenticated to getRestaurantById!");
            }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public ResponseEntity<List<Restaurants>> getAllRestaurants(String token) throws AuthenticationException {
        if (Boolean.TRUE.equals(jwtTokenInterface.validateToken(token).getBody())) {
            return retrieveAllRestaurants();
        } else {
            throw new AuthenticationException("User is not authenticated!");
        }
    }

    public ResponseEntity<List<Restaurants>> retrieveAllRestaurants() {
        try {
            return new ResponseEntity<>(restaurantRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

}
