package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.entity.UserEntity;

public interface LoginService {
    boolean checkLogin(String email, String password);
    UserEntity checkLogin(String email);
}
