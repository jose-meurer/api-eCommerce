package com.josemeurer.ecommerce.services;

import com.josemeurer.ecommerce.dto.ProductDTO;
import com.josemeurer.ecommerce.models.Product;
import com.josemeurer.ecommerce.repositories.CategoryRepository;
import com.josemeurer.ecommerce.repositories.ProductRepository;
import com.josemeurer.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(UUID id) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return new ProductDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> page = repository.findAll(pageable);
        return page.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        dto.getCategories().forEach(x ->
                entity.getCategories().add(categoryRepository.getReferenceById(x.getId())));
    }
}
