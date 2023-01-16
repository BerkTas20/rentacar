package com.berktas.rentacar.model.dto.address;

import lombok.Data;

@Data
public class AddressDto {
    private String country;
    private String province;
    private String district;
    private String neighborhood;
    private String street;
    private String buildingInformation;
    private String fullAddress;
}
