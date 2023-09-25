package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client editClient(Long id, Client editedClient) {
        // Check if the client with the given ID exists
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            editedClient.setId(id);
            return clientRepository.save(editedClient);
        } else {
            throw new RuntimeException("Client not found");
        }
    }
}
