package com.example.fooddeliveryapp.repository;

import com.example.fooddeliveryapp.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {
}
