package com.example.fooddeliveryapp.controller;

import com.example.fooddeliveryapp.entity.CategoryEntity;
import com.example.fooddeliveryapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<?> getExploreCategory() {
        List<CategoryEntity> categoryEntities = categoryService.getExplorer();
        return new ResponseEntity<>(categoryEntities, HttpStatus.OK);
    }
}
