package com.restaurante.demo.controller;


import com.restaurante.demo.model.Product;
import com.restaurante.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID uuid){
        return new ResponseEntity<Product>(productService.getProduct(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID uuid, @RequestBody Product product){
        Product p = productService.updateProduct(uuid, product);
        return new ResponseEntity<Product>(p, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Product> deleteProduct(@PathVariable UUID uuid){
        productService.deleteProduct(uuid);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
}
