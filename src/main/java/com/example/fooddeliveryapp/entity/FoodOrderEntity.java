package com.example.fooddeliveryapp.entity;

import javax.persistence.*;

@Entity(name="FoodOrder")
@Table(name="food_order")
public class FoodOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_order")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name="id_food")
    private FoodEntity food;

    @Column(name="price")
    private float price;

    @Column(name="quality")
    private int quality;

    @Column(name = "id_promo")
    private int promoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }
}
