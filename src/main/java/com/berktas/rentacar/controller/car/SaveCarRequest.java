package com.berktas.rentacar.controller.car;

import com.berktas.rentacar.model.entity.embedded.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;


@Getter
@Setter
@Builder
@Jacksonized
public class SaveCarRequest {
    public String carName;
    public int modelYear;
    public double dailyPrice;
    public String description;
    public String licencePlate;
    private String imei;
    private String year;
    private Address address;
    private long totalDistance;
}
