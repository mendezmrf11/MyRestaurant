package com.restaurante.demo.service;

import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements IRestaurantService{
    @Override
    public String findAllClients() {
        return "Messi bebe";
    }
}
