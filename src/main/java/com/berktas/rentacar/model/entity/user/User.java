package com.berktas.rentacar.model.entity.user;

import com.berktas.rentacar.controller.user.SaveUserRequest;
import com.berktas.rentacar.controller.user.UpdateUserRequest;
import com.berktas.rentacar.core.validator.phone.Phone;
import com.berktas.rentacar.model.entity.base.AbstractTimestampEntity;
import com.berktas.rentacar.model.entity.embedded.Address;
import com.berktas.rentacar.model.enums.Role;
import com.berktas.rentacar.model.enums.UserType;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class User extends AbstractTimestampEntity {

    private String firstName;

    private String lastName;

    @NotNull
    private String username;

    private String email;

//    @Setter(AccessLevel.PRIVATE)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Phone
    private String phone;

    @Embedded
    private Address address;

    @Column(updatable = false, insertable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String firebaseToken;

    public static User create(SaveUserRequest saveUserRequest){
        User user = new User();
        user.setUsername(saveUserRequest.getUsername());
        user.setFirstName(saveUserRequest.getFirstName());
        user.setLastName(saveUserRequest.getLastName());
        user.setRole(saveUserRequest.getRole());
        user.setPassword(saveUserRequest.getPassword());
        user.setEmail(saveUserRequest.getEmail());
        return user;

    }

    public static User update(UpdateUserRequest updateUserRequest){
        User user = new User();
        user.setUsername(updateUserRequest.getUsername());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setEmail(updateUserRequest.getEmail());
        return user;
    }

    public void changePassword(String rawPassword) {
        password = new BCryptPasswordEncoder().encode(rawPassword);
    }

    public User updatePassword(String password) {
        this.password = password;
        return this;
    }
}
