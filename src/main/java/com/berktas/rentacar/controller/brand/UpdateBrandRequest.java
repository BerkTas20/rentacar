package com.berktas.rentacar.controller.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateBrandRequest {
    public String brandName;
}
