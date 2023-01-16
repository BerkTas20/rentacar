package com.berktas.rentacar.mapper;


import com.berktas.rentacar.mapper.base.BaseMapper;
import com.berktas.rentacar.model.dto.CustomerDto;
import com.berktas.rentacar.model.entity.user.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper<CustomerDto, Customer> {
}
