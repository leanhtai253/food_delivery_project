package com.example.fooddeliveryapp.entity;

import javax.persistence.*;

@Entity(name="OrderStatus")
@Table(name="order_status")
public class OrderStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_order")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name="id_status")
    private StatusEntity status;

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

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
