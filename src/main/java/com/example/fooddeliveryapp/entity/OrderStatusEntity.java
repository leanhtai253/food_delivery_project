package com.example.fooddeliveryapp.entity;

import com.example.fooddeliveryapp.entity.Id.OrderStatusId;

import javax.persistence.*;

@Entity(name="OrderStatus")
@Table(name="order_status")
@IdClass(OrderStatusId.class)
public class OrderStatusEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @Id
    @Column(name="id_order")
    private int idOrder;

    @Id
    @Column(name="id_status")
    private int idStatus;

    @ManyToOne
    @JoinColumn(name="id_order", updatable = false, insertable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name="id_status", updatable = false, insertable = false)
    private StatusEntity status;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
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
