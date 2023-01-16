package com.berktas.rentacar.mapper;

import com.berktas.rentacar.mapper.base.BaseMapper;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.model.entity.Car;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CarMapper extends BaseMapper<CarDto, Car> {
}
