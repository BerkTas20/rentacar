package com.berktas.rentacar.mapper;

import com.berktas.rentacar.mapper.base.BaseMapper;
import com.berktas.rentacar.model.dto.OnlyCarInformationDto;
import com.berktas.rentacar.model.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OnlyVehicleInformationMapper extends BaseMapper<OnlyCarInformationDto, Car> {

}
