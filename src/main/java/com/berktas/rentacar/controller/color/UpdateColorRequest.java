package com.berktas.rentacar.controller.color;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateColorRequest {
    public String colorName;
}
