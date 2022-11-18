package com.example.fooddeliveryapp.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Order")
@Table(name="t_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    private Set<FoodOrderEntity> foodOrders;

    @Column(name="estimate_ship")
    private String estimateShip;

    @Column(name="delivery_address")
    private String deliveryAddress;

    @OneToMany(mappedBy = "order")
    private Set<OrderStatusEntity> statuses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<FoodOrderEntity> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(Set<FoodOrderEntity> foodOrders) {
        this.foodOrders = foodOrders;
    }

    public String getEstimateShip() {
        return estimateShip;
    }

    public void setEstimateShip(String estimateShip) {
        this.estimateShip = estimateShip;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<OrderStatusEntity> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<OrderStatusEntity> statuses) {
        this.statuses = statuses;
    }
}
