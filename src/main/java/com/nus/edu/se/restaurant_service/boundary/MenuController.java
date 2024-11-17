package com.nus.edu.se.restaurant_service.boundary;

import com.nus.edu.se.restaurant_service.model.Menus;
import com.nus.edu.se.restaurant_service.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("menuAPI")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Transactional
    @GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menus>> getMenus(HttpServletRequest request) throws AuthenticationException {
        String token = menuService.resolveToken(request);
        return menuService.getMenus(token);
    }

    @GetMapping("getMenuById/{id}")
    public ResponseEntity<Menus> getMenuById(@PathVariable String id, HttpServletRequest request) throws AuthenticationException {
        String token = menuService.resolveToken(request);
        return menuService.getMenuById(id, token);
    }
}
