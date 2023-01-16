package com.berktas.rentacar.utils;




import com.berktas.rentacar.controller.user.SaveUserRequest;
import com.berktas.rentacar.model.entity.user.Admin;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.model.enums.Role;
import com.berktas.rentacar.model.enums.UserType;
import com.berktas.rentacar.repository.AdminRepository;
import com.berktas.rentacar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestUserUtility {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    public Admin getOrCreateTestAdmin() {
        return adminRepository.findByUsername("admin").orElseGet(() -> {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setFirstName("Admin");
            admin.setLastName("Adminnn");
            admin.setPassword(new BCryptPasswordEncoder().encode("12345"));
            admin.setUserType(UserType.Admin);
            return adminRepository.save(admin);
        });
    }

    public User getOrCreateTestUser() {
        return userRepository.findByUsername("user").orElseGet(() -> {
            User admin = User.create(SaveUserRequest.builder()
                    .username("user")
                    .firstName("Test_User")
                    .lastName("testLastName")
                    .password("12345")
                    .role(Role.ROLE_ADMIN)
                    .build());
            return userRepository.save(admin);
        });
    }
}
