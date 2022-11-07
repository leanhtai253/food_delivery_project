package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.entity.UserEntity;
import com.example.fooddeliveryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImp implements LoginService{
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean checkLogin(String email, String password) {
        List<UserEntity> users = userRepository.findByEmailAndPassword(email, password);
        return users.size() > 0;
    }
}
