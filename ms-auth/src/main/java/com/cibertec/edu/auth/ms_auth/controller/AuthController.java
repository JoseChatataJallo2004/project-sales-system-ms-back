package com.cibertec.edu.auth.ms_auth.controller;

import com.cibertec.edu.auth.ms_auth.service.CustomUserDetailsService;
import com.cibertec.edu.auth.ms_auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private  AuthenticationManager authManager;
    @Autowired
    private JwtUtil jwt;
    @Autowired
    private  CustomUserDetailsService uds;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest req) {

        // 1. validar credenciales
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));

        // 2. cargar usuario y generar token
        var userDetails = uds.loadUserByUsername(req.username());
        String token = jwt.generateToken(
                userDetails.getUsername(),
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList());

        // 3. devolver token
        return Map.of("access_token", token);
    }

}

record AuthRequest(String username, String password) {}