package com.berktas.rentacar.controller.customer;

import com.berktas.rentacar.core.validator.phone.Phone;
import com.berktas.rentacar.model.entity.embedded.Address;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class SaveCustomerRequest {
    private Address address;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String title;
    public String companyName;
}
