package com.example.fooddeliveryapp.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name="restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="image")
    private String image;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private Set<FoodEntity> foods;

    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantReviewEntity> reviews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<FoodEntity> getFoods() {
        return foods;
    }

    public void setFoods(Set<FoodEntity> foods) {
        this.foods = foods;
    }

    public Set<RestaurantReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(Set<RestaurantReviewEntity> reviews) {
        this.reviews = reviews;
    }
}
