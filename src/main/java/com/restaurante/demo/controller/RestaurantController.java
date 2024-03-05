package com.restaurante.demo.controller;

import com.restaurante.demo.service.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController(value = "/clients")
public class RestaurantController {

    @Autowired
    RestaurantServiceImpl restaurantService;

    @GetMapping(path = "/{document}")
    public String getRestaurant(){
        return restaurantService.findAllClients();
    }
}
