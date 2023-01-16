package com.berktas.rentacar.repository;

import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Long countByRole(Role role);

}
