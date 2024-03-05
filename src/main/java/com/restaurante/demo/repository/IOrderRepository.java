package com.restaurante.demo.repository;

import com.restaurante.demo.model.Order;
import com.restaurante.demo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {

    Optional<Order> findByUuid(UUID uuid);
}
