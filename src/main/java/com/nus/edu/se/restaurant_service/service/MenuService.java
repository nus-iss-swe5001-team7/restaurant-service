package com.nus.edu.se.restaurant_service.service;

import com.nus.edu.se.restaurant_service.dao.MenuRepository;
import com.nus.edu.se.restaurant_service.model.Menus;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    JwtTokenInterface jwtTokenInterface;

    public ResponseEntity<Menus> retrieveMenuById(String id) {
        try {
            Optional<Menus> menu = menuRepository.findById(id);
            return menu.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Menus(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Menus> getMenuById(String id, String token) throws AuthenticationException {
            if (Boolean.TRUE.equals(jwtTokenInterface.validateToken(token).getBody())) {
                return retrieveMenuById(id);
            } else {
                throw new AuthenticationException("User is not authenticated to getMenuById!");
            }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public ResponseEntity<List<Menus>> getMenus(String token) throws AuthenticationException {
        if (Boolean.TRUE.equals(jwtTokenInterface.validateToken(token).getBody())) {
            return retrieveAllMenus();
        } else {
            throw new AuthenticationException("User is not authenticated for getMenus!");
        }
    }

    public ResponseEntity<List<Menus>> retrieveAllMenus() {
        try {
            return new ResponseEntity<>(menuRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

}
