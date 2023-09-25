package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        // Define mock behavior before each test
        List<Product> products = new ArrayList<>();
        // Use the existing constructor to create Product instances
        products.add(new Product(1L, "Product 1", "Description 1", BigDecimal.valueOf(10.0), "Category 1", LocalDate.now()));

        // Mock the findAll method
        when(productRepository.findAll()).thenReturn(products);

        // Mock the findById method
        Long productId = 1L;
        Product product = new Product(productId, "Product 1", "Description 1", BigDecimal.valueOf(10.0), "Category 1", LocalDate.now());
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Mock the save method
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product savedProduct = invocation.getArgument(0);
            savedProduct.setId(2L); // Simulate saving with a new ID
            return savedProduct;
        });
    }

    @Test
    public void testGetAllProducts() {
        // Call the service method
        List<Product> products = productService.getAllProducts();

        // Verify the result
        assertEquals(1, products.size());
    }

    @Test
    public void testGetProductById() {
        // Call the service method
        Long productId = 1L;
        Optional<Product> product = productService.getProductById(productId);

        // Verify the result
        assertEquals(productId, product.orElseThrow().getId());
    }

    @Test
    public void testCreateProduct() {
        // Create a new product
        Product newProduct = new Product(null, "Product 2", "Description 2", BigDecimal.valueOf(20.0), "Category 2", LocalDate.now());

        // Call the service method to create the product
        Product createdProduct = productService.createProduct(newProduct);

        // Verify the result (ID should be assigned)
        assertEquals(2L, createdProduct.getId());
    }

    @Test
    public void testUpdateProduct() {
        // Define an existing product and an edited product
        Long productId = 1L;
        Product existingProduct = new Product(productId, "Product 1", "Description 1", BigDecimal.valueOf(10.0), "Category 1", LocalDate.now());
        Product editedProduct = new Product(productId, "Edited Product", "Edited Description", BigDecimal.valueOf(15.0), "Edited Category", LocalDate.now());

        // Mock the findById method to return the existing product
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        // Call the service method to edit the product
        Product updatedProduct = productService.updateProduct(productId, editedProduct);

        // Verify the result (should match the edited product)
        assertEquals(editedProduct, updatedProduct);
    }
}

