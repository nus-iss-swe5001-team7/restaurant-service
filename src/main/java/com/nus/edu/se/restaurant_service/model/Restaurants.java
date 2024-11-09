package com.nus.edu.se.restaurant_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurants {

    @Id
    private String id;
    private String restaurantName;
    private String cuisineType;
    private String location;
    private float rating;
    private String restaurantImgURL;
    private List<Menus> menus;
    private String restaurantAddress;
    private String restaurantLatitude;
    private String restaurantLongitude;


    public Restaurants(String restaurantName,
                       String restaurantId,
                       String restaurantAddress,
                       String restaurantLatitude,
                       String restaurantLongitude,
                       String cuisineType,
                       String location,
                       float rating,
                       String restaurantImgURL,
                       List<Menus> menus) {
        this.restaurantName = restaurantName;
        this.id = restaurantId;
        this.restaurantAddress = restaurantAddress;
        this.restaurantLatitude = restaurantLatitude;
        this.restaurantLongitude = restaurantLongitude;
        this.cuisineType = cuisineType;
        this.location = location;
        this.rating = rating;
        this.restaurantImgURL = restaurantImgURL;
        this.menus = menus;
    }
}
