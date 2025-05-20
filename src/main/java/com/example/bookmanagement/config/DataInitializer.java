package com.example.bookmanagement.config;

import com.example.bookmanagement.entity.User;
import com.example.bookmanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if admin account exists
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoles(new HashSet<>(Arrays.asList("ADMIN", "USER")));
                userRepository.save(admin);
                System.out.println("Admin user created");
            }

            // Check if regular user account exists
            if (!userRepository.existsByUsername("user")) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user"));
                user.setRoles(new HashSet<>(Arrays.asList("USER")));
                userRepository.save(user);
                System.out.println("Regular user created");
            }
        };
    }
}