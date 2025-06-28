package com.cibertec.edu.auth.ms_auth.config;

import com.cibertec.edu.auth.ms_auth.model.User;
import com.cibertec.edu.auth.ms_auth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
@Component @RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private  UserRepository repo;
    @Autowired
    private  PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            repo.save(User.builder()
                    .username("admin")
                    .password(encoder.encode("admin123"))
                    .roles("ADMIN")
                    .build());
        }
    }
}