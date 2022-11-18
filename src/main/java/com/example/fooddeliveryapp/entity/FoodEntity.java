package com.example.fooddeliveryapp.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name="food")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="image")
    private String image;

    @Column(name="price")
    private int price;

    @ManyToOne
    @JoinColumn(name="id_category")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name="id_restaurant")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "food")
    private Set<FoodAddonEntity> foodAddons;

    @OneToMany(mappedBy = "food")
    private Set<FoodMaterialEntity> foodMaterials;

    @OneToMany(mappedBy = "food")
    private Set<FoodOrderEntity> foodOrders;

    @OneToMany(mappedBy = "food")
    private Set<FoodReviewEntity> reviews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public Set<FoodAddonEntity> getFoodAddons() {
        return foodAddons;
    }

    public void setFoodAddons(Set<FoodAddonEntity> foodAddons) {
        this.foodAddons = foodAddons;
    }

    public Set<FoodMaterialEntity> getFoodMaterials() {
        return foodMaterials;
    }

    public void setFoodMaterials(Set<FoodMaterialEntity> foodMaterials) {
        this.foodMaterials = foodMaterials;
    }

    public Set<FoodOrderEntity> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(Set<FoodOrderEntity> foodOrders) {
        this.foodOrders = foodOrders;
    }

    public Set<FoodReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(Set<FoodReviewEntity> reviews) {
        this.reviews = reviews;
    }
}
