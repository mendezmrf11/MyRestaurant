package com.restaurante.demo.controller;

import com.restaurante.demo.dto.OrderDto;
import com.restaurante.demo.model.Order;
import com.restaurante.demo.service.OrderServiceImpl;
import org.apache.coyote.Response;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto){
        Order order = OrderDto.fromDto(orderDto);
        return new ResponseEntity<Order>(orderService.createOrder(order), HttpStatus.CREATED );
    }

    @PatchMapping("/{uuid}/delivered/{localDate}")
    public ResponseEntity<Order> updateOrder(@PathVariable UUID uuid, LocalDate localDate){
        return new ResponseEntity<Order>(orderService.updateOrder(uuid, localDate), HttpStatus.NO_CONTENT);
    }
}
