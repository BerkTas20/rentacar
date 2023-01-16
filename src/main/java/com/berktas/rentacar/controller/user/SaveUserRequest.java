package com.berktas.rentacar.controller.user;

import com.berktas.rentacar.model.enums.Role;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class SaveUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Role role;
}
