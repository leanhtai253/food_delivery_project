package com.example.fooddeliveryapp.entity.Id;

import javax.persistence.Column;
import java.io.Serializable;

public class FoodMaterialId implements Serializable {
    @Column(name="id_food")
    private int idFood;

    @Column(name="id_material")
    private int idMaterial;

    public FoodMaterialId(int idFood, int idMaterial) {
        this.idFood = idFood;
        this.idMaterial = idMaterial;
    }
}
