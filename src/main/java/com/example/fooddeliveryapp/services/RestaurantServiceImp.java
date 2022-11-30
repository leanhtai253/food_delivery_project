package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.RestaurantDTO;
import com.example.fooddeliveryapp.dto.RestaurantDetailDTO;
import com.example.fooddeliveryapp.entity.RestaurantEntity;
import com.example.fooddeliveryapp.entity.RestaurantReviewEntity;
import com.example.fooddeliveryapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService{
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantDTO> getRestaurants() {
        List<RestaurantDTO> restaurants = new ArrayList<>();
        List<RestaurantEntity> entities = restaurantRepository.findAll();
        for (RestaurantEntity entity : entities) {
            RestaurantDTO dto = new RestaurantDTO();
            dto.setName(entity.getName());
            dto.setImage(entity.getImage());
            float rating = 0;
            float sumRate = 0;
            if (entity.getReviews().size() > 0) {
                for (RestaurantReviewEntity review : entity.getReviews()) {
                    sumRate += review.getRate();
                }
                rating = sumRate / entity.getReviews().size();
            }
            dto.setAvgRating(rating);
            restaurants.add(dto);
        }
        return restaurants;
    }

    @Override
    public RestaurantDetailDTO getRestaurantDetail(int id) {
        RestaurantDetailDTO dto = new RestaurantDetailDTO();
        // Có hoặc không có cũng được (dữ liệu có thể bị null)
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        // Nếu có giá trị
        if (restaurantEntity.isPresent()) {
            dto.setName(restaurantEntity.get().getName());
            dto.setImage(restaurantEntity.get().getImage());
            dto.setDesc("");
            float rating = 0;
            float sumRate = 0;
            if (restaurantEntity.get().getReviews().size() > 0) {
                for (RestaurantReviewEntity review : restaurantEntity.get().getReviews()) {
                    sumRate += review.getRate();
                }
                rating = sumRate / restaurantEntity.get().getReviews().size();
            }
            dto.setAvgRating(rating);
        }
        return dto;
    }
}
