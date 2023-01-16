package com.berktas.rentacar.business.abstracts;


import com.berktas.rentacar.controller.user.SaveUserRequest;
import com.berktas.rentacar.controller.user.UpdatePasswordRequest;
import com.berktas.rentacar.controller.user.UpdateUserRequest;
import com.berktas.rentacar.model.dto.UserDto;
import com.berktas.rentacar.model.entity.user.User;


import java.util.List;

public interface UserService {
    UserDto save(SaveUserRequest saveUserRequest);

    UserDto update(Long id, UpdateUserRequest updateUserRequest);

    void delete(Long id);

    List<UserDto> getAll();

    User findById(Long id);

    UserDto getById(Long id);

    void updateUserPassword(Long id, UpdatePasswordRequest request);

}
