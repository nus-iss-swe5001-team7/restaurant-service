package com.nus.edu.se.restaurant_service.entity;

import com.nus.edu.se.restaurant_service.model.Menus;
import com.nus.edu.se.restaurant_service.model.Restaurants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.assertions.Assertions.assertNull;
import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantMenuTest {

    private Restaurants restaurant;
    private List<Menus> menus;

    @BeforeEach
    void setUp() {

        menus = Arrays.asList(new Menus("Pizza", "pizzaId", "pizza", 10.99f, "snacks", true, "pizzaurl"),
                new Menus("Pasta", "pastaId", "pasta", 7.99f, "noodle", true, "pastaurl"));

        restaurant = new Restaurants(
                "Italian Bistro",
                "1234",
                "123 Main St",
                "40.7128N",
                "74.0060W",
                "Italian",
                "New York",
                4.5f,
                "http://image.url",
                menus
        );
    }

    @Test
    void testRestaurantConstructor() {
        assertNotNull(restaurant);
        assertEquals("Italian Bistro", restaurant.getRestaurantName());
        assertEquals("1234", restaurant.getId());
        assertEquals("123 Main St", restaurant.getRestaurantAddress());
        assertEquals("40.7128N", restaurant.getRestaurantLatitude());
        assertEquals("74.0060W", restaurant.getRestaurantLongitude());
        assertEquals("Italian", restaurant.getCuisineType());
        assertEquals("New York", restaurant.getLocation());
        assertEquals(4.5f, restaurant.getRating());
        assertEquals("http://image.url", restaurant.getRestaurantImgURL());
        assertEquals(menus, restaurant.getMenus());
    }

    @Test
    void testSettersAndGetters() {

        restaurant.setRestaurantName("New Italian Bistro");
        assertEquals("New Italian Bistro", restaurant.getRestaurantName());

        restaurant.setCuisineType("Mexican");
        assertEquals("Mexican", restaurant.getCuisineType());

        restaurant.setLocation("Los Angeles");
        assertEquals("Los Angeles", restaurant.getLocation());

        restaurant.setRating(4.7f);
        assertEquals(4.7f, restaurant.getRating());

        restaurant.setRestaurantImgURL("http://newimage.url");
        assertEquals("http://newimage.url", restaurant.getRestaurantImgURL());

        List<Menus> newMenus = Arrays.asList(new Menus("Tacos", "tacosId", "Tacos", 7.99f, "snacks", true, "Tacosurl"));
        restaurant.setMenus(newMenus);
        assertEquals(newMenus, restaurant.getMenus());
    }

    @Test
    void testToStringMethod() {
        String expectedString = "Restaurants(id=1234, restaurantName=Italian Bistro, " +
                "cuisineType=Italian, location=New York, rating=4.5, restaurantImgURL=http://image.url, " +
                "menus=" + menus + ", restaurantAddress=123 Main St, " +
                "restaurantLatitude=40.7128N, restaurantLongitude=74.0060W)";

        assertEquals(expectedString, restaurant.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Restaurants anotherRestaurant = new Restaurants(
                "Italian Bistro", "1234", "123 Main St", "40.7128N", "74.0060W",
                "Italian", "New York", 4.5f, "http://image.url", menus
        );


        assertEquals(restaurant, anotherRestaurant);
        assertEquals(restaurant.hashCode(), anotherRestaurant.hashCode());
    }

    @Test
    void testNoArgsConstructor() {

        Restaurants emptyRestaurant = new Restaurants();
        assertNotNull(emptyRestaurant);
        assertNull(emptyRestaurant.getRestaurantName());
        assertNull(emptyRestaurant.getId());
        assertNull(emptyRestaurant.getRestaurantAddress());
        assertNull(emptyRestaurant.getRestaurantLatitude());
        assertNull(emptyRestaurant.getRestaurantLongitude());
        assertNull(emptyRestaurant.getCuisineType());
        assertNull(emptyRestaurant.getLocation());
        assertEquals(0f, emptyRestaurant.getRating());
        assertNull(emptyRestaurant.getRestaurantImgURL());
        assertNull(emptyRestaurant.getMenus());
    }
}
