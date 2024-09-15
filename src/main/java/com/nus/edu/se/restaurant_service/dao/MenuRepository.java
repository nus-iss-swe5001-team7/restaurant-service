package com.nus.edu.se.restaurant_service.dao;


import com.nus.edu.se.restaurant_service.model.Menus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuRepository extends MongoRepository<Menus, String> {
}
