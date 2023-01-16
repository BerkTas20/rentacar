package com.berktas.rentacar.mapper;

import com.berktas.rentacar.mapper.base.BaseMapper;
import com.berktas.rentacar.model.dto.ColorDto;
import com.berktas.rentacar.model.entity.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper extends BaseMapper<ColorDto, Color> {
}
