package com.berktas.rentacar.model.dto;

import com.berktas.rentacar.model.dto.base.BaseDto;
import com.berktas.rentacar.model.dto.base.TimestampBaseDto;
import com.berktas.rentacar.model.enums.Role;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;


@Getter
@SuperBuilder
@ToString
@Jacksonized
public class UserDto extends TimestampBaseDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Role role;
}
