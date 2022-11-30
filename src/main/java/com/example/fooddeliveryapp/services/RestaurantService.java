package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.RestaurantDTO;
import com.example.fooddeliveryapp.dto.RestaurantDetailDTO;

import java.util.List;

public interface RestaurantService {
    public List<RestaurantDTO> getRestaurants();
    public RestaurantDetailDTO getRestaurantDetail(int id);
}
