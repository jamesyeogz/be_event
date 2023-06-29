package com.example.eventapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.example.eventapp.security.filter.AuthenticationFilter;
import com.example.eventapp.security.filter.ExceptionHandlerFilter;
import com.example.eventapp.security.filter.JwtAuthenticationFilter;
import com.example.eventapp.security.manager.CustomAuthenticationManager;


import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
public class SecurityConfig{
    @Autowired
    CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter=new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/user/login");
        http
        .cors(cors-> cors.disable())
        .headers(header -> header.frameOptions(frameOptions -> frameOptions.disable()))
        .csrf((csrf) -> csrf.disable())
        // .csrf().disable().authorizeRequests().anyRequest().permitAll()
        // .httpBasic((basic) -> basic.disable())
        .authorizeHttpRequests(auth -> {
            // auth.requestMatchers(HttpMethod.OPTIONS).permitAll();
            auth.requestMatchers(HttpMethod.POST,"/user/register").permitAll();
            auth.requestMatchers(toH2Console()).permitAll();
            // auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
            auth.anyRequest().authenticated();
        })
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
        .addFilter(authenticationFilter)
        .addFilterAfter(new JwtAuthenticationFilter(), AuthenticationFilter.class);

        return http.build();
    }
    
}
