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

    public Restaurants(String restaurantName,
                       String cuisineType,
                       String location,
                       float rating,
                       String restaurantImgURL,
                       List<Menus> menus) {
        this.restaurantName = restaurantName;
        this.cuisineType = cuisineType;
        this.location = location;
        this.rating = rating;
        this.restaurantImgURL = restaurantImgURL;
        this.menus = menus;
    }
}
