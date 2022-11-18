package com.example.fooddeliveryapp.entity;

import javax.persistence.*;

@Entity(name="FoodReview")
@Table(name="food_review")
public class FoodReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="content")
    private String content;

    @Column(name="createDate")
    private String createDate;

    @Column(name="rate")
    private float rate;

    @ManyToOne
    @JoinColumn(name="id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="id_food")
    private FoodEntity food;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }
}
