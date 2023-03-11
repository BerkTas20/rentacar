package com.berktas.rentacar.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentFuelPricesDto {
    private String priceGasoline;

    private String priceDiesel;

    private String priceGas;
}
