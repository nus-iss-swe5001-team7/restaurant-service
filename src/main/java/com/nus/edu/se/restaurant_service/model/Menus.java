package com.nus.edu.se.restaurant_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menus")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menus {
    @Id
    private String id;
    private String menuName;
    private String description;
    private float menuPrice;
    private String category;
    private boolean available = true;
    private String menuImageURL;

    public Menus(String menuName,
                 String description,
                 float menuPrice,
                 String category,
                 boolean available,
                 String menuImageURL) {
        this.menuName = menuName;
        this.description = description;
        this.menuPrice = menuPrice;
        this.category = category;
        this.available = available;
        this.menuImageURL = menuImageURL;
    }
}
