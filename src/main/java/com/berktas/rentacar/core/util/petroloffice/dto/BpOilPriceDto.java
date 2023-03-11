package com.berktas.rentacar.core.util.petroloffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BpOilPriceDto {
    private String provinceName;
    private String districtName;
}
