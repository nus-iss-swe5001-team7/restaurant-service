package com.nus.edu.se.restaurant_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurants {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer restaurantId;

    @Id
    @UuidGenerator
    @Column(name = "restaurant_id", nullable = false)
    private UUID id;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

}
