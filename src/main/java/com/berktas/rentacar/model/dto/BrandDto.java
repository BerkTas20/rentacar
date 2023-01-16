package com.berktas.rentacar.model.dto;

import com.berktas.rentacar.model.dto.base.TimestampBaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Getter
@SuperBuilder
@ToString
@Jacksonized
public class BrandDto extends TimestampBaseDto {

    public String brandName;


}
