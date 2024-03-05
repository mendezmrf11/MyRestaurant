package com.restaurante.demo.service;

import com.restaurante.demo.model.Client;
import com.restaurante.demo.repository.IClientRepository;
import com.restaurante.demo.utils.ClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    IClientRepository clientRepository;

    @Override
    public Client getClientByDocument(String documentClient) {
        ClientValidation.documentValidation(documentClient);

        Optional<Client> client = clientRepository.findByDocument(documentClient);

        ClientValidation.clientEmptyValidation(client, documentClient);

        return client.get();
    }

    @Override
    public Client createClient(Client client) {
        ClientValidation.clientTotalValidation(client);

        Optional<Client> existClient = clientRepository.findByDocument(client.getDocument());

        ClientValidation.clientPresentValidation(existClient, client.getDocument());
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(String documentClient, Client newClient) {
        Optional<Client> existingClientOptional = clientRepository.findByDocument(documentClient);

        ClientValidation.clientEmptyValidation(existingClientOptional, documentClient);
        ClientValidation.clientEqualValidation(existingClientOptional.get(), newClient);
        ClientValidation.clientTotalValidation(newClient);

        Client existingClient = existingClientOptional.get();

        existingClient.setName(newClient.getName());
        existingClient.setEmail(newClient.getEmail());
        existingClient.setPhone(newClient.getPhone());
        existingClient.setDeliveryAddress(newClient.getDeliveryAddress());

        return clientRepository.save(existingClient);
    }

    @Override
    public Client deleteClient(String documentClient) {
        ClientValidation.documentValidation(documentClient);

        Optional<Client> deletedClient = clientRepository.findByDocument(documentClient);
        ClientValidation.clientEmptyValidation(deletedClient, documentClient);

        Client clientToDelete = deletedClient.get();
        clientRepository.delete(clientToDelete);
        return clientToDelete;
    }
}
