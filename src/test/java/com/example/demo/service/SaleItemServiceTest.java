package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.Sale;
import com.example.demo.model.SaleItem;
import com.example.demo.repository.SaleItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SaleItemServiceTest {

    private SaleItemService saleItemService;

    @Mock
    private SaleItemRepository saleItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        saleItemService = new SaleItemService(saleItemRepository);
    }

    @Test
    public void testCreateSaleItem() {
        // Create a sample SaleItem to be saved
        SaleItem saleItemToSave = createSampleSaleItem(null);

        // Define mock behavior
        SaleItem savedSaleItem = createSampleSaleItem(1L); // Simulate saving with a new ID
        when(saleItemRepository.save(saleItemToSave)).thenReturn(savedSaleItem);

        // Call the service method to create the sale item
        SaleItem createdSaleItem = saleItemService.createSaleItem(saleItemToSave);

        // Verify the result (ID should be assigned)
        assertEquals(1L, createdSaleItem.getId());
    }

    @Test
    public void testGetAllSaleItems() {
        // Define mock behavior
        List<SaleItem> saleItems = new ArrayList<>();
        saleItems.add(createSampleSaleItem(1L)); // Create a sample SaleItem with ID 1
        when(saleItemRepository.findAll()).thenReturn(saleItems);

        // Call the service method
        Iterable<SaleItem> result = saleItemService.getAllSaleItems();

        // Verify the result
        assertEquals(1, ((List<SaleItem>) result).size());
    }

    private SaleItem createSampleSaleItem(Long id) {
        Long productId = 1L;
        Product product = new Product(productId, "Product 1", "Description 1", BigDecimal.valueOf(10.0), "Category 1", LocalDate.now());
        Sale sale = new Sale(1L, null, null, null, BigDecimal.valueOf(100.0), new ArrayList<>());
        return new SaleItem(id, sale, product, BigDecimal.valueOf(50.0), 2);
    }
}

