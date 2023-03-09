package com.berktas.rentacar.controller.car;

import com.berktas.rentacar.model.entity.embedded.Address;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Getter
@Setter
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
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
