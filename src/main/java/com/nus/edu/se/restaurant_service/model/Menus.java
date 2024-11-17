package com.nus.edu.se.restaurant_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "menus")
@Data
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

    private Map<String, List<String>> preferences;

    public enum SpiceLevel {
        NONE, MILD, SPICY
    }

    public enum IceLevel {
        LESS, NORMAL, MORE
    }

    @Getter
    public enum SweetnessLevel {
        ZERO_PERCENT("0%"),
        TWENTY_FIVE_PERCENT("25%"),
        FIFTY_PERCENT("50%"),
        SEVENTY_FIVE_PERCENT("75%"),
        ONE_HUNDRED_PERCENT("100%");

        private final String displayValue;

        SweetnessLevel(String displayValue) {
            this.displayValue = displayValue;
        }
    }


    public Menus(String menuName,
                 String menuId,
                 String description,
                 float menuPrice,
                 String category,
                 boolean available,
                 String menuImageURL) {
        this.menuName = menuName;
        this.id = menuId;
        this.description = description;
        this.menuPrice = menuPrice;
        this.category = category;
        this.available = available;
        this.menuImageURL = menuImageURL;
    }

    public Menus(String menuName,
                 String menuId,
                 String description,
                 float menuPrice,
                 String category,
                 boolean available,
                 String menuImageURL,
                 Map<String, List<String>> preferences) {
        this.menuName = menuName;
        this.id = menuId;
        this.description = description;
        this.menuPrice = menuPrice;
        this.category = category;
        this.available = available;
        this.menuImageURL = menuImageURL;
        this.preferences = preferences != null ? preferences : new HashMap<>();
    }
}
