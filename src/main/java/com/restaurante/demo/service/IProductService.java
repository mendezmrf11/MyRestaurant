package com.restaurante.demo.service;

import com.restaurante.demo.model.Client;
import com.restaurante.demo.model.Product;

import java.util.UUID;

public interface IProductService {
    public Product createProduct(Product product);
    public Product getProduct(UUID uuidProduct);
    public Product updateProduct(UUID uuid, Product product);
    public Product deleteProduct(UUID uuid);
}
