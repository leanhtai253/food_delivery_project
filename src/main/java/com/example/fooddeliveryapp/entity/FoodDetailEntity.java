package com.example.fooddeliveryapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="FoodDetail")
@Table(name="food_detail")
public class FoodDetailEntity {
    @Id
    @Column(name="id_food")
    private int foodId;

    @Column(name="description")
    private String description;

    @Column(name="create_date")
    private String createDate;

    @Column(name="rating")
    private float rating;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
