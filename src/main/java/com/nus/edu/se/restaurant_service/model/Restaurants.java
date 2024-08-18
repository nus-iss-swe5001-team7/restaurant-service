package com.nus.edu.se.restaurant_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;

    private String restaurantName;

}
