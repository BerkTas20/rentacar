package com.berktas.rentacar.mapper;

import com.berktas.rentacar.mapper.base.BaseMapper;
import com.berktas.rentacar.model.dto.UserDto;
import com.berktas.rentacar.model.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User> {
}
