package com.nus.edu.se.restaurant_service;

import com.nus.edu.se.restaurant_service.dao.MenuRepository;
import com.nus.edu.se.restaurant_service.dao.RestaurantRepository;
import com.nus.edu.se.restaurant_service.model.Menus;
import com.nus.edu.se.restaurant_service.model.Restaurants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.TRUE;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class RestaurantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        return args -> {
            long menuCount = menuRepository.count();
            long restaurantCount = restaurantRepository.count();

            if (menuCount == 0 && restaurantCount == 0) {
                initializeData(menuRepository, restaurantRepository);
                System.out.println("Sample data initialized.");
            } else {
                System.out.println("Data already exists. Initialization skipped.");
            }
        };
    }

    private void initializeData(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        Menus menu1 = new Menus("Nasi Lemak",
                "Coconut milk rice served with sambal, fried chicken, boiled egg, cucumber, and fried anchovies",
                10.99f,
                "Main Course",
                TRUE,
                "https://images.pexels.com/photos/11912788/pexels-photo-11912788.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
        Menus menu2 = new Menus("Satay",
                "Grilled skewered meat served with peanut sauce",
                8.50f,
                "Appetizer",
                TRUE,
                "https://images.pexels.com/photos/19792082/pexels-photo-19792082/free-photo-of-food-plate-wood-dinner.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
        Menus menu3 = new Menus("Laksa",
                "Spicy noodle soup with coconut milk, shrimp, chicken, and tofu",
                11.99f,
                "Main Course",
                TRUE,
                "https://images.pexels.com/photos/9772442/pexels-photo-9772442.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");

        menuRepository.insert(Arrays.asList(menu1, menu2, menu3));

        Restaurants restaurant1 = new Restaurants("Malay Delight",
                "Malay",
                "North",
                4.0f,
                "https://images.pexels.com/photos/11912788/pexels-photo-11912788.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                new ArrayList<>(Arrays.asList(menu1, menu2, menu3)));

        restaurantRepository.insert(restaurant1);

        Menus menu4 = new Menus("Steamed Dumplings",
                "Delicious steamed dumplings filled with pork and cabbage.",
                8.99f,
                "Appetizers",
                TRUE,
                "https://images.pexels.com/photos/7251866/pexels-photo-7251866.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");

        Menus menu5 = new Menus("Pan-Fried Dumplings",
                "Crispy pan-fried dumplings filled with savory pork and chives.",
                9.99f,
                "Appetizers",
                TRUE,
                "https://images.pexels.com/photos/7287723/pexels-photo-7287723.jpeg?auto=compress&cs=tinysrgb&w=1200");

        Menus menu6 = new Menus("Dumpling Noodle Soup",
                "Noodle soup served with delicious dumplings, vegetables, and broth.",
                11.99f,
                "Main Course",
                TRUE,
                "https://images.pexels.com/photos/5409015/pexels-photo-5409015.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");

        menuRepository.insert(Arrays.asList(menu4, menu5, menu6));


        Restaurants restaurant2 = new Restaurants("Dumpling House",
                "Chinese",
                "South",
                3.0f,
                "https://images.pexels.com/photos/7363691/pexels-photo-7363691.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                new ArrayList<>(Arrays.asList(menu4, menu5, menu6)));

        restaurantRepository.insert(restaurant2);

        Menus menu7 = new Menus("Tom Yum Soup",
                "Traditional Thai hot and sour soup with shrimp, lemongrass, and chili.",
                9.99f,
                "Soups",
                TRUE,
                "https://images.pexels.com/photos/10398943/pexels-photo-10398943.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");

        Menus menu8 = new Menus("Pad Thai",
                "Stir-fried rice noodles with shrimp, tofu, bean sprouts, and peanuts.",
                11.99f,
                "Noodles",
                TRUE,
                "https://images.pexels.com/photos/12481161/pexels-photo-12481161.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");

        Menus menu9 = new Menus("Mango Sticky Rice",
                "Sweet sticky rice topped with ripe mango slices and coconut milk.",
                7.99f,
                "Desserts",
                TRUE,
                "https://images.pexels.com/photos/19856579/pexels-photo-19856579/free-photo-of-food-plate-wood-dinner.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");

        Menus menu10 = new Menus("Thai Iced Tea",
                "Sweet and creamy Thai iced tea with condensed milk.",
                3.99f,
                "Beverages",
                TRUE,
                "https://images.pexels.com/photos/11100423/pexels-photo-11100423.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");

        menuRepository.insert(Arrays.asList(menu7, menu8, menu9, menu10));

        Restaurants restaurant3 = new Restaurants("Thai Spice",
                "Thai",
                "Central",
                4.0f,
                "https://images.pexels.com/photos/12255224/pexels-photo-12255224.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                new ArrayList<>(Arrays.asList(menu7, menu8, menu9, menu10)));

        restaurantRepository.insert(restaurant3);

        Menus menu11 = new Menus("Tandoori Chicken Rice",
                "Tandoori Chicken Rice",
                10f,
                "Tandoori",
                TRUE,
                "https://images.pexels.com/photos/20642812/pexels-photo-20642812/free-photo-of-biryani.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

        menuRepository.insert(menu11);

        Restaurants restaurant4 = new Restaurants("Eastern Tandoori Palace",
                "Indian",
                "East",
                4.0f,
                "https://images.pexels.com/photos/9792458/pexels-photo-9792458.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                new ArrayList<>(List.of(menu11)));

        restaurantRepository.insert(restaurant4);

        Menus menu12 = new Menus("Thai Iced Tea",
                "Sweet and creamy Thai iced tea with condensed milk.",
                3.99f,
                "Beverages",
                TRUE,
                "https://images.pexels.com/photos/11100423/pexels-photo-11100423.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");

        menuRepository.insert(menu12);

        Restaurants restaurant5 = new Restaurants("West Tempura House",
                "Japanese",
                "West",
                4.0f,
                "https://images.pexels.com/photos/2098131/pexels-photo-2098131.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                new ArrayList<>(List.of(menu12)));

        restaurantRepository.insert(restaurant5);
    }
}
