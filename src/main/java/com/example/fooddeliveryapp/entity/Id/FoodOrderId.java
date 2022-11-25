package com.example.fooddeliveryapp.entity.Id;

import java.io.Serializable;

public class FoodOrderId implements Serializable {
    private int idFood;
    private int idOrder;

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
}
