package com.josemeurer.ecommerce.dto;

import com.josemeurer.ecommerce.models.Category;

import java.io.Serializable;
import java.util.UUID;

public class CategoryDTO implements Serializable {

    private UUID id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category entity) {
        this(entity.getId(), entity.getName());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
