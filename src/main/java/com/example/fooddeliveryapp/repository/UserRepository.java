package com.example.fooddeliveryapp.repository;

import com.example.fooddeliveryapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    List<UserEntity> findByEmailAndPassword(String email, String password);
    List<UserEntity> findByEmail(String email);
}
