package com.restaurante.demo.service;

import com.restaurante.demo.model.Order;

import java.time.LocalDate;
import java.util.UUID;

public interface IOrderService {

    public Order createOrder(Order order);
    Order updateOrder(UUID uuid, LocalDate localDate);
}
