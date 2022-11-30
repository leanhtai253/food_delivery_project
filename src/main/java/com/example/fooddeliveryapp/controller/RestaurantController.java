package com.example.fooddeliveryapp.controller;

import com.example.fooddeliveryapp.dto.RestaurantDTO;
import com.example.fooddeliveryapp.dto.RestaurantDetailDTO;
import com.example.fooddeliveryapp.entity.RestaurantEntity;
import com.example.fooddeliveryapp.payload.response.ResponseData;
import com.example.fooddeliveryapp.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    ResponseData responseData = new ResponseData();
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<?> getRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.getRestaurants();
        System.out.println("Kiem tra getRestaurants: "+restaurants.size());
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setSuccess(true);
        responseData.setData(restaurants);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantDetails(@PathVariable("id") int id) {
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setSuccess(true);
        RestaurantDetailDTO dto = restaurantService.getRestaurantDetail(id);
        responseData.setData(dto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
