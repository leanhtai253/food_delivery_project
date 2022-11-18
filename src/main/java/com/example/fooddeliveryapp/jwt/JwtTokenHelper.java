package com.example.fooddeliveryapp.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenHelper {
    private long tokenDuration = 8 * 60 * 60 * 1000;
    private final String strKey = "YW5oIHTDoGkgxJHhurlwIHRyYWkgxJHhuqNtIMSRYW5nIGjhu41jIGdp4buPaSBuaOG6pXQgdGjhur8gZ2lhbiBs4bqrbiB2xakgdHLhu6U=";
    public String generateToken(String data) {
        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + tokenDuration);
        SecretKey sk = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey)); // mã hóa luôn cả secret key
        return Jwts.builder()
                .setSubject(data) // Lưu trữ dữ liệu vào token, kiểu String
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
}
