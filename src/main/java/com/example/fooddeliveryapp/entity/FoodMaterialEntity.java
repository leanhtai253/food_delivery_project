package com.example.fooddeliveryapp.entity;

import com.example.fooddeliveryapp.entity.Id.FoodMaterialId;

import javax.persistence.*;

@Entity(name="FoodMaterial")
@Table(name="food_material")
@IdClass(FoodMaterialId.class)
public class FoodMaterialEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @Id
    @Column(name="id_food")
    private int idFood;

    @Id
    @Column(name="id_material")
    private int idMaterial;

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

        @ManyToOne
    @JoinColumn(name="id_food", updatable = false, insertable = false)
    private FoodEntity food;

    @ManyToOne
    @JoinColumn(name="id_material", updatable = false, insertable = false)
    private MaterialEntity material;

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
