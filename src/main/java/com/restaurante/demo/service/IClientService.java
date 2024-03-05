package com.restaurante.demo.service;


import com.restaurante.demo.model.Client;
import org.springframework.web.servlet.function.EntityResponse;

public interface IClientService {

    Client getClientByDocument(String documentClient);

    public Client createClient(Client client);
    public Client updateClient(String documentClient, Client client);
    public Client deleteClient(String documentClient);
}
