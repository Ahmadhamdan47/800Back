package com.example.demo.service;

import com.example.demo.model.SaleItem;
import com.example.demo.repository.SaleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleItemService {

    private final SaleItemRepository saleItemRepository;

    @Autowired
    public SaleItemService(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    public SaleItem createSaleItem(SaleItem saleItem) {
        return saleItemRepository.save(saleItem);
    }

    public Iterable<SaleItem> getAllSaleItems() {
        return saleItemRepository.findAll();
    }
    
    
    // Add other methods for managing sale items as needed
}
