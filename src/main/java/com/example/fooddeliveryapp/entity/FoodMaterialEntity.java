package com.example.fooddeliveryapp.entity;

import javax.persistence.*;

@Entity(name="FoodMaterial")
@Table(name="food_material")
public class FoodMaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_food")
    private FoodEntity food;

    @ManyToOne
    @JoinColumn(name="id_material")
    private MaterialEntity material;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }
}
