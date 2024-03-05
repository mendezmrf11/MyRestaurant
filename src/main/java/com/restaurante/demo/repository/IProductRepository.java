package com.restaurante.demo.repository;

import com.restaurante.demo.model.Product;
import jakarta.persistence.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends CrudRepository<Product, UUID> {
    Optional<Product> findByFantasyName(String fantasyName);
    Optional<Product> findByUuid(UUID uuid);

}
