package com.restaurante.demo.controller;

import com.restaurante.demo.model.Client;
import com.restaurante.demo.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;

    @GetMapping("/messi")
    public String messi(){
        return "Messi";
    }

    @GetMapping("/{documentClient}")
    public ResponseEntity<Client> getClient(@PathVariable("documentClient") String documentClient){
        return new ResponseEntity<Client>(clientService.getClientByDocument(documentClient), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client newClient = clientService.createClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping("/{documentClient}")
    public ResponseEntity<Client> updateClient(@PathVariable("documentClient") String documentClient, @RequestBody Client client){
        Client updateClient = clientService.updateClient(documentClient, client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{documentClient}")
    public ResponseEntity<Client> deleteClient(@PathVariable("documentClient") String documentClient){
        Client deletedClient = clientService.deleteClient(documentClient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
