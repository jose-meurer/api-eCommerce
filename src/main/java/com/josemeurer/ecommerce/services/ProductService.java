package com.josemeurer.ecommerce.services;

import com.josemeurer.ecommerce.dto.ProductDTO;
import com.josemeurer.ecommerce.models.Product;
import com.josemeurer.ecommerce.repositories.ProductRepository;
import com.josemeurer.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(UUID id) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return new ProductDTO(entity);
    }
}
