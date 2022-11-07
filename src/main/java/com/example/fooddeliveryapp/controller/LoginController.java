package com.example.fooddeliveryapp.controller;

import com.example.fooddeliveryapp.payload.request.SignInRequest;
import com.example.fooddeliveryapp.payload.response.ResponseData;
import com.example.fooddeliveryapp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/signin")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setSuccess(loginService.checkLogin(request.getUsername(), request.getPassword()));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
