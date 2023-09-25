package com.example.demo.service;

import com.example.demo.model.Sale;
import com.example.demo.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final Logger logger = LoggerFactory.getLogger(SaleService.class); // Logger instance

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public Sale createSale(Sale sale) {
        Sale createdSale = saleRepository.save(sale);

        // Log the creation operation
        logger.info("Sale created with ID: {}", createdSale.getId());

        return createdSale;
    }

    public Sale editSale(Long id, Sale editedSale) {
        // Check if the sale with the given ID exists
        Optional<Sale> existingSaleOptional = saleRepository.findById(id);
        if (existingSaleOptional.isPresent()) {
            Sale existingSale = existingSaleOptional.get();

            // Update sale properties
            existingSale.setCreationDate(editedSale.getCreationDate());
            existingSale.setClient(editedSale.getClient());
            existingSale.setSeller(editedSale.getSeller());
            existingSale.setTotal(editedSale.getTotal());
            existingSale.setSaleItems(editedSale.getSaleItems());

            // Save the updated sale
            Sale updatedSale = saleRepository.save(existingSale);

            // Log the update operation
            logger.info("Sale with ID {} has been updated: {}", id, updatedSale);

            return updatedSale;
        } else {
            throw new RuntimeException("Sale not found");
        }
    }

    public void deleteSale(Long id) {
        // Check if the sale with the given ID exists
        Optional<Sale> existingSaleOptional = saleRepository.findById(id);
        if (existingSaleOptional.isPresent()) {
            // Delete the sale
            saleRepository.deleteById(id);

            // Log the delete operation
            logger.info("Sale with ID {} has been deleted", id);
        } else {
            throw new RuntimeException("Sale not found");
        }
    }

}
