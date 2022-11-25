package com.example.fooddeliveryapp.security;

import com.example.fooddeliveryapp.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class
        );
        authManagerBuilder.authenticationProvider(authProvider);
        return authManagerBuilder.build();
    }

    // Mã hóa dữ liệu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* Nơi quy định các rules liên quan tới bảo mật và quyền truy cập... */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /* antMatchers: đường link cần phải filter
        *  authenticated: bắt buộc phải đăng nhập mới được truy cập
        *  permitAll: cho phép mọi roles truy cập vào
        *  anyRequest: truy cập bất kỳ link nào */
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/refresh-token").permitAll()
                .antMatchers("/file/downloadFile/**").permitAll()
                .antMatchers("/signin/test").authenticated()
                .anyRequest().authenticated();
        // Config TokenFilter trước
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
