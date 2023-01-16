package com.berktas.rentacar.controller.account;

import com.berktas.rentacar.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int userType;
    private String username;
    private Role role;

}
