package com.restaurante.demo.repository;

import com.restaurante.demo.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByDocument(String document);

}
