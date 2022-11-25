package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.entity.CategoryEntity;
import com.example.fooddeliveryapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryEntity> findTop6() {
        return categoryRepository.findTop6();
    }
}
