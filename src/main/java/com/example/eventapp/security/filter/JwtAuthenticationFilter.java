package com.example.eventapp.security.filter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.eventapp.security.SecurityConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain ) throws ServletException,IOException{
        String header = request.getHeader("Authorization");
        // System.out.println(request.getRequestURI().toString());
        // System.out.println(SecurityConstants.REGISTER_PATH);
        // System.out.println(request.getRequestURI().equals(SecurityConstants.REGISTER_PATH));
        if(request.getRequestURI().equals(SecurityConstants.REGISTER_PATH) || request.getRequestURI().contains("h2")){
            chain.doFilter(request, response);
            return;
        }
        // System.out.println(request.getRequestURI());
        // System.out.println(request.getRequestURL());
        // if(request.getRequestURL().toString() == SecurityConstants.REGISTER_PATH){
        //     System.out.println(request.getRequestURI());
        //     System.out.println(request.getRequestURL());
        // }
        // if(header == null || !header.startsWith(SecurityConstants.BEARER)){
        //     // There can be a better way to do this
        //     // How to bypass authentication inside with the login page?
        //     chain.doFilter(request, response);
        //     return;
        // }
        String token = header.replace(SecurityConstants.BEARER, "");
        String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
        .build()
        .verify(token)
        .getSubject();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,Arrays.asList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }
    
}
