package com.berktas.rentacar.core.util.opet;

import lombok.Data;

import java.util.List;

@Data
public class OpetChildDto {
    private String districtCode;
    private String districtName;
    private String provinceCode;
    private String provinceName;
    private List<OpetPricesDto> prices;
}
