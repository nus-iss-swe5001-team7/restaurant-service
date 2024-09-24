package com.nus.edu.se.restaurant_service.boundary;

import com.nus.edu.se.restaurant_service.dao.MenuRepository;
import com.nus.edu.se.restaurant_service.model.Menus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("menuAPI")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Transactional
    @GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menus> getMenus() {
        return menuRepository.findAll();
    }

    @GetMapping("getMenuById/{id}")
    public Optional<Menus> getMenuById(@PathVariable String id) {
        return menuRepository.findById(id);
    }
}
