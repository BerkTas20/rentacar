package com.berktas.rentacar.controller.user;

import com.berktas.rentacar.model.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Role role;
}
