package com.example.demo.controller;

import com.example.demo.model.Sale;
import com.example.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // GET request to retrieve all sales
    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    // GET request to retrieve a sale by ID
    @GetMapping("/{id}")
    public Optional<Sale> getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id);
    }

    // POST request to create a new sale
    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

    // PUT request to edit an existing sale
    @PutMapping("/{id}")
    public Sale editSale(@PathVariable Long id, @RequestBody Sale editedSale) {
        return saleService.editSale(id, editedSale);
    }
}