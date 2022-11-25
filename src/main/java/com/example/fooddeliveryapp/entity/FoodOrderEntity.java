package com.example.fooddeliveryapp.entity;

import com.example.fooddeliveryapp.entity.Id.FoodOrderId;

import javax.persistence.*;

@Entity(name="FoodOrder")
@Table(name="food_order")
@IdClass(FoodOrderId.class)
public class FoodOrderEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id
    @Column(name="id_food")
    private int idFood;

    @Id
    @Column(name="id_order")
    private int idOrder;

    @ManyToOne
    @JoinColumn(name="id_order", updatable = false, insertable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name="id_food", updatable = false, insertable = false)
    private FoodEntity food;

    @Column(name="price")
    private float price;

    @Column(name="quality")
    private int quality;

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
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

}
