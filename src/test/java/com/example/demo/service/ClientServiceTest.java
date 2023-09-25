package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        // Define mock behavior before each test
        List<Client> clients = new ArrayList<>();
        // Use the existing constructor to create Client instances
        clients.add(new Client(1L, "John", "Doe", "1234567890", new ArrayList<>()));

        // Mock the findAll method
        when(clientRepository.findAll()).thenReturn(clients);

        // Mock the findById method
        Long clientId = 1L;
        Client client = new Client(clientId, "John", "Doe", "1234567890", new ArrayList<>());
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // Mock the save method
        when(clientRepository.save(any(Client.class))).thenAnswer(invocation -> {
            Client savedClient = invocation.getArgument(0);
            savedClient.setId(2L); // Simulate saving with a new ID
            return savedClient;
        });
    }

    @Test
    public void testGetAllClients() {
        // Call the service method
        List<Client> clients = clientService.getAllClients();

        // Verify the result
        assertEquals(1, clients.size());
    }

    @Test
    public void testGetClientById() {
        // Call the service method
        Long clientId = 1L;
        Optional<Client> client = clientService.getClientById(clientId);

        // Verify the result
        assertEquals(clientId, client.orElseThrow().getId());
    }

    @Test
    public void testCreateClient() {
        // Create a new client
        Client newClient = new Client(null, "Jane", "Doe", "9876543210", new ArrayList<>());

        // Call the service method to create the client
        Client createdClient = clientService.createClient(newClient);

        // Verify the result (ID should be assigned)
        assertEquals(2L, createdClient.getId());
    }

    @Test
    public void testEditClient() {
        // Define an existing client and an edited client
        Long clientId = 1L;
        Client existingClient = new Client(clientId, "John", "Doe", "1234567890", new ArrayList<>());
        Client editedClient = new Client(clientId, "Jane", "Doe", "9876543210", new ArrayList<>());

        // Mock the findById method to return the existing client
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));

        // Call the service method to edit the client
        Client updatedClient = clientService.editClient(clientId, editedClient);

        // Verify the result (should match the edited client)
        assertEquals(editedClient, updatedClient);
    }
}