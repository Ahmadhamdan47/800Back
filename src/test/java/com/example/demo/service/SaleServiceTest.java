package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Sale;
import com.example.demo.model.User;
import com.example.demo.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SaleServiceTest {

    private SaleService saleService;

    @Mock
    private SaleRepository saleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        saleService = new SaleService(saleRepository);
    }

    @Test
    public void testGetAllSales() {
        // Define mock behavior
        List<Sale> sales = new ArrayList<>();
        sales.add(createSampleSale(1L)); // Create a sample Sale with ID 1
        when(saleRepository.findAll()).thenReturn(sales);

        // Call the service method
        List<Sale> result = saleService.getAllSales();

        // Verify the result
        assertEquals(1, result.size());
    }

    @Test
    public void testGetSaleById() {
        // Define mock behavior
        Long saleId = 1L;
        Sale sale = createSampleSale(saleId);
        when(saleRepository.findById(saleId)).thenReturn(Optional.of(sale));

        // Call the service method
        Optional<Sale> result = saleService.getSaleById(saleId);

        // Verify the result
        assertEquals(saleId, result.orElseThrow().getId());
    }

    @Test
    public void testCreateSale() {
        // Create a sample Sale to be saved
        Sale saleToSave = createSampleSale(null);

        // Define mock behavior
        Sale savedSale = createSampleSale(1L); // Simulate saving with a new ID
        when(saleRepository.save(saleToSave)).thenReturn(savedSale);

        // Call the service method to create the sale
        Sale createdSale = saleService.createSale(saleToSave);

        // Verify the result (ID should be assigned)
        assertEquals(1L, createdSale.getId());
    }

    @Test
    public void testEditSale() {
        // Define mock behavior
        Long saleId = 1L;
        Sale existingSale = createSampleSale(saleId);
        Sale editedSale = createSampleSale(saleId); // Edited sale with the same ID

        when(saleRepository.findById(saleId)).thenReturn(Optional.of(existingSale));
        when(saleRepository.save(existingSale)).thenReturn(editedSale);

        // Call the service method to edit the sale
        Sale updatedSale = saleService.editSale(saleId, editedSale);

        // Verify the result (should match the edited sale)
        assertEquals(editedSale, updatedSale);
    }

    @Test
    public void testDeleteSale() {
        // Define mock behavior
        Long saleId = 1L;
        Sale existingSale = createSampleSale(saleId);

        when(saleRepository.findById(saleId)).thenReturn(Optional.of(existingSale));

        // Call the service method to delete the sale
        saleService.deleteSale(saleId);

        // Verify that the deleteById method was called
        verify(saleRepository, times(1)).deleteById(saleId);
    }

    private Sale createSampleSale(Long id) {
        Client client = new Client(1L, "John", "Doe", "1234567890", new ArrayList<>());
        User seller = new User(1L, "Seller", "seller@example.com", "password", new ArrayList<>());
        return new Sale(id, LocalDate.now(), client, seller, BigDecimal.valueOf(100.0), new ArrayList<>());
    }
}

