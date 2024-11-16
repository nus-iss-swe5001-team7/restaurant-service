package com.nus.edu.se.restaurant_service.boundary;

import com.nus.edu.se.restaurant_service.model.Menus;
import com.nus.edu.se.restaurant_service.service.JwtTokenInterface;
import com.nus.edu.se.restaurant_service.service.MenuService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MenuServiceTest {
    @InjectMocks
    private MenuController menuController;

    @Mock
    private MenuService menuService;

    @Mock
    private HttpServletRequest request;

    private Menus menu1;
    private Menus menu2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Create some dummy menu data
        menu1 = new Menus("Pizza", "pizzaId", "pizza", 10.99f, "snacks", true, "pizzaurl");
        menu2 = new Menus("Pasta", "pastaId", "pasta", 7.99f, "noodle", true, "pastaurl");
    }

    // Test for success: When menus are fetched successfully
    @Test
    void testGetMenus_Success() throws AuthenticationException {
        // Arrange: Mock the behavior of MenuService to return a list of menus
        List<Menus> menuList = Arrays.asList(menu1, menu2);
        when(menuService.resolveToken(request)).thenReturn("validToken");  // Mock the token resolution
        when(menuService.getMenus("validToken")).thenReturn(new ResponseEntity<>(menuList, HttpStatus.OK));  // Mock the service response

        // Act: Call the getMenus method in the controller (passing the mocked request)
        ResponseEntity<List<Menus>> response = menuController.getMenus(request);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "The status code should be 200 OK");
        assertEquals(menuList, response.getBody(), "The response body should contain the list of menus");

        // Verify that the service methods were called
        verify(menuService, times(1)).resolveToken(request);
        verify(menuService, times(1)).getMenus("validToken");
    }

    // Test for failure: When the MenuService returns an error response (e.g., empty list with BAD_REQUEST status)
    @Test
    void testGetMenus_Failure_BadRequest() throws AuthenticationException {
        // Arrange: Simulate a failure scenario where MenuService returns a BAD_REQUEST status
        when(menuService.resolveToken(request)).thenReturn("validToken");  // Mock the token resolution
        when(menuService.getMenus("validToken")).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));  // Mock the service response

        // Act: Call the getMenus method with a valid token
        ResponseEntity<List<Menus>> response = menuController.getMenus(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "The status code should be 400 BAD_REQUEST");

        // Verify that the service methods were called
        verify(menuService, times(1)).resolveToken(request);
        verify(menuService, times(1)).getMenus("validToken");
    }
}
