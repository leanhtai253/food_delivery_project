package com.example.fooddeliveryapp.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper {
    private Gson gson = new Gson();
    private final String strKey = "YW5oIHTDoGkgxJHhurlwIHRyYWkgxJHhuqNtIMSRYW5nIGjhu41jIGdp4buPaSBuaOG6pXQgdGjhur8gZ2lhbiBs4bqrbiB2xakgdHLhu6U=";
    public String generateToken(String data, String type, long duration) {
        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + duration);
        SecretKey sk = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey)); // mã hóa luôn cả secret key
        Map<String,Object> subjectData = new HashMap<>();
        subjectData.put("username",data);
        subjectData.put("type",type);

        String json = gson.toJson(subjectData);

        return Jwts.builder()
                .setSubject(json) // Lưu trữ dữ liệu vào token, kiểu String
                .setIssuedAt(now) // thời gian tạo ra token
                .setExpiration(dateExpired)
                .signWith(sk,SignatureAlgorithm.HS256)
                .compact(); // trả ra token đã mã hóa
    }

    public String decode(String token) {
        SecretKey sk = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        return Jwts.parserBuilder()
                .setSigningKey(sk)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validate(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        boolean isSuccess = false;
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            isSuccess = true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return isSuccess;
    }

    public String getStrKey() {
        return strKey;
    }
}
