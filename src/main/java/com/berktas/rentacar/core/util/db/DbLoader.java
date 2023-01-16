package com.berktas.rentacar.core.util.db;

import com.berktas.rentacar.controller.user.SaveUserRequest;
import com.berktas.rentacar.model.entity.user.Admin;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.model.enums.Role;
import com.berktas.rentacar.model.enums.UserType;
import com.berktas.rentacar.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DbLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Long adminCount = userRepository.countByRole(Role.ROLE_ADMIN);
        if (adminCount == 0) {
            User admin = User.create(SaveUserRequest.builder()
                    .firstName("Admin")
                    .username("admin")
                    .password("12345")
                            .role(Role.ROLE_ADMIN)
                    .build());
            userRepository.save(admin);
        }
    }
}
