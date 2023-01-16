package com.berktas.rentacar.controller.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@Jacksonized
public class SaveBrandRequest {
    public String brandName;
}
