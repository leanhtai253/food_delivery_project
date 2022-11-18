package com.example.fooddeliveryapp.controller;

import com.example.fooddeliveryapp.jwt.JwtTokenFilter;
import com.example.fooddeliveryapp.jwt.JwtTokenHelper;
import com.example.fooddeliveryapp.payload.request.SignInRequest;
import com.example.fooddeliveryapp.payload.response.ResponseData;
import com.example.fooddeliveryapp.payload.response.TokenResponseData;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/refresh-token")
public class RefreshTokenController {
    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    private final long rTokenDuration = 80 * 60 * 60 * 1000;
    private final long tokenDuration = 8 * 60 * 60 * 1000;
    private Gson gson = new Gson();
    @PostMapping("")
    public ResponseEntity<?> refreshToken(@RequestParam("token") String token) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setSuccess(true);

        if (jwtTokenHelper.validate(token)) {

            String json = jwtTokenHelper.decode(token);
            Map<String,Object> map = gson.fromJson(json,Map.class);
            if (StringUtils.hasText(map.get("type").toString())
                    && map.get("type").equals("refresh")) {
                String aToken = jwtTokenHelper.generateToken(map.get("username").toString(), "token", tokenDuration);
                String rToken = jwtTokenHelper.generateToken(map.get("username").toString(), "refresh", rTokenDuration);

                TokenResponseData tokenData = new TokenResponseData();
                tokenData.setToken(aToken);
                tokenData.setRefreshToken(rToken);

                responseData.setData(tokenData);
            }
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
