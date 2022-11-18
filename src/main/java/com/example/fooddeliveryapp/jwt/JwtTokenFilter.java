package com.example.fooddeliveryapp.jwt;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extract token from Header
        String token = getTokenFromHeader(request);
        System.out.println("Token: " + token);
        if (token != null) {
            if (jwtTokenHelper.validate(token)) {

                String json = jwtTokenHelper.decode(token);

                Map<String,Object> map = gson.fromJson(json,Map.class);
                System.out.println("username:"+json+"-"+map.get("type").toString());
                String username = jwtTokenHelper.decode(token);
                if (StringUtils.hasText(map.get("type").toString())
                    && !map.get("type").equals("refresh")) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    "","", new ArrayList<>()
                            );
                    SecurityContext sc = SecurityContextHolder.getContext();
                    sc.setAuthentication(auth);
                }
            }
        }
        filterChain.doFilter(request,response);
    }

    public String getTokenFromHeader(HttpServletRequest request) {
        // Get Token from field Authorization from Header
        String strToken = request.getHeader("Authorization");
        if (StringUtils.hasText(strToken) && strToken.startsWith("Bearer ")) {
            return strToken.substring(7);
        } else {
            return null;
        }
    }
}
