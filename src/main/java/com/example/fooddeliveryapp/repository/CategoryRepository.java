package com.example.fooddeliveryapp.repository;

import com.example.fooddeliveryapp.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = "select c from category c order by c.id DESC limit 6", nativeQuery = true)
    public List<CategoryEntity> findTop6();
}
