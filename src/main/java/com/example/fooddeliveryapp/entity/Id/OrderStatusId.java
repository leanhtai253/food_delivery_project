package com.example.fooddeliveryapp.entity.Id;

import java.io.Serializable;

public class OrderStatusId implements Serializable {
    private int idOrder;
    private int idStatus;

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
}
