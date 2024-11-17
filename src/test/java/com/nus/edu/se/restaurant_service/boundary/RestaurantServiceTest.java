package com.nus.edu.se.restaurant_service.boundary;

import com.nus.edu.se.restaurant_service.dao.RestaurantRepository;
import com.nus.edu.se.restaurant_service.model.Restaurants;
import com.nus.edu.se.restaurant_service.service.JwtTokenInterface;
import com.nus.edu.se.restaurant_service.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class RestaurantServiceTest {
    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private JwtTokenInterface jwtTokenInterface;

    @Mock
    private HttpServletRequest request;

    private Restaurants restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

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
                null
        );
    }

    @Test
    void testGetRestaurantByName_Success() {
        // Arrange
        when(restaurantRepository.findByRestaurantName("Italian Bistro")).thenReturn(restaurant);

        // Act
        ResponseEntity<Restaurants> response = restaurantService.getRestaurantByName("Italian Bistro");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Italian Bistro", response.getBody().getRestaurantName());
        verify(restaurantRepository, times(1)).findByRestaurantName("Italian Bistro");
    }

    @Test
    void testGetRestaurantByName_NotFound() {
        // Arrange
        when(restaurantRepository.findByRestaurantName("Non Existent Restaurant")).thenReturn(null);

        // Act
        ResponseEntity<Restaurants> response = restaurantService.getRestaurantByName("Non Existent Restaurant");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRetrieveRestaurantById_Success() {
        // Arrange
        when(restaurantRepository.findById("1234")).thenReturn(Optional.of(restaurant));

        // Act
        ResponseEntity<Restaurants> response = restaurantService.retrieveRestaurantById("1234");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1234", response.getBody().getId());
        verify(restaurantRepository, times(1)).findById("1234");
    }

    @Test
    void testRetrieveRestaurantById_NotFound() {
        // Arrange
        when(restaurantRepository.findById("1234")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Restaurants> response = restaurantService.retrieveRestaurantById("1234");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetRestaurantById_Unauthenticated() {
        // Arrange
        String token = "invalid-token";
        when(jwtTokenInterface.validateToken(token)).thenReturn(new ResponseEntity<>(false, HttpStatus.OK));

        // Act & Assert
        assertThrows(AuthenticationException.class, () -> {
            restaurantService.getRestaurantById("1234", token);
        });
    }

    @Test
    void testGetRestaurantById_SuccessWithValidToken() throws AuthenticationException {
        // Arrange
        String token = "valid-token";
        when(jwtTokenInterface.validateToken(token)).thenReturn(new ResponseEntity<>(true,HttpStatus.OK));
        when(restaurantRepository.findById("1234")).thenReturn(Optional.of(restaurant));

        // Act
        ResponseEntity<Restaurants> response = restaurantService.getRestaurantById("1234", token);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1234", response.getBody().getId());
        verify(jwtTokenInterface, times(1)).validateToken(token);
        verify(restaurantRepository, times(1)).findById("1234");
    }

    @Test
    void testResolveToken_Success() {
        // Arrange
        String bearerToken = "Bearer valid-token";
        when(request.getHeader("Authorization")).thenReturn(bearerToken);

        // Act
        String token = restaurantService.resolveToken(request);

        // Assert
        assertEquals("valid-token", token);
    }

    @Test
    void testResolveToken_NoToken() {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        String token = restaurantService.resolveToken(request);

        // Assert
        assertNull(token);
    }

    @Test
    void testGetAllRestaurants_Success() throws AuthenticationException {
        // Arrange
        String token = "valid-token";
        List<Restaurants> restaurantsList = Arrays.asList(restaurant);
        when(jwtTokenInterface.validateToken(token)).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));
        when(restaurantRepository.findAll()).thenReturn(restaurantsList);

        // Act
        ResponseEntity<List<Restaurants>> response = restaurantService.getAllRestaurants(token);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Italian Bistro", response.getBody().get(0).getRestaurantName());
    }

    @Test
    void testGetAllRestaurants_Unauthenticated() {
        // Arrange
        String token = "invalid-token";
        when(jwtTokenInterface.validateToken(token)).thenReturn(new ResponseEntity<>(false,HttpStatus.OK));

        // Act & Assert
        assertThrows(AuthenticationException.class, () -> {
            restaurantService.getAllRestaurants(token);
        });
    }

    @Test
    void testRetrieveAllRestaurants_Failure() {
        // Arrange
        when(restaurantRepository.findAll()).thenThrow(new RuntimeException("Database Error"));

        // Act
        ResponseEntity<List<Restaurants>> response = restaurantService.retrieveAllRestaurants();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
