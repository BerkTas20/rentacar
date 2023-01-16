package com.berktas.rentacar.mapper;


import com.berktas.rentacar.mapper.base.BaseMapper;
import com.berktas.rentacar.model.dto.BrandDto;
import com.berktas.rentacar.model.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper extends BaseMapper<BrandDto, Brand> {
}


