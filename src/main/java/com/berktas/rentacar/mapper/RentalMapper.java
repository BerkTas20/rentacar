package com.berktas.rentacar.mapper;

import com.berktas.rentacar.mapper.base.BaseMapper;
import com.berktas.rentacar.model.dto.RentalDetailDto;
import com.berktas.rentacar.model.entity.Rental;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RentalMapper extends BaseMapper<RentalDetailDto, Rental> {
}
