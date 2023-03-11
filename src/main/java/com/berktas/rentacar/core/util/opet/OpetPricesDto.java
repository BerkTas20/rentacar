package com.berktas.rentacar.core.util.opet;

import lombok.Data;

@Data
public class OpetPricesDto {
    private Double amount;
    private String id;
    private String productCode;
    private String productName;
    private String productShortName;
}
