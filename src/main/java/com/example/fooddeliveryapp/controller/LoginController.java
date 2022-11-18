package com.example.fooddeliveryapp.controller;

import com.example.fooddeliveryapp.jwt.JwtTokenHelper;
import com.example.fooddeliveryapp.payload.request.SignInRequest;
import com.example.fooddeliveryapp.payload.request.TokenRequest;
import com.example.fooddeliveryapp.payload.response.ResponseData;
import com.example.fooddeliveryapp.payload.response.TokenResponseData;
import com.example.fooddeliveryapp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/signin")
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    private long tokenDuration = 8 * 60 * 60 * 1000;
    private long rTokenDuration = 80 * 60 * 60 * 1000;

    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request, HttpServletResponse resp) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        Authentication auth =  authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        String token = jwtTokenHelper.generateToken(request.getUsername(), "token", tokenDuration);
        String refreshToken = jwtTokenHelper.generateToken(request.getUsername()+"_refresh","refresh",rTokenDuration);
        String tokenDecoded = jwtTokenHelper.decode(token);

        TokenResponseData tokenData = new TokenResponseData();
        tokenData.setToken(token);
        tokenData.setRefreshToken(refreshToken);

        Cookie cookie = new Cookie("tokenSignin", token);
        resp.addCookie(cookie);

        ResponseData responseData = new ResponseData();
        if (auth != null) {
            responseData.setSuccess(true);
        }
        responseData.setData(tokenData);
        responseData.setDesc(tokenDecoded);
        responseData.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/readtoken")
    public ResponseEntity<?> readToken(@CookieValue(value="tokenSignin") String token) {
        String data = jwtTokenHelper.decode(token);

        ResponseData responseData = new ResponseData();
        responseData.setData(data);
        responseData.setSuccess(true);
        responseData.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String signInTest() {
        return "Hello Test";
    }
}
