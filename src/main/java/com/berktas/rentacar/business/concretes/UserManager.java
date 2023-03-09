package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.UserService;
import com.berktas.rentacar.controller.user.SaveUserRequest;
import com.berktas.rentacar.controller.user.UpdatePasswordRequest;
import com.berktas.rentacar.controller.user.UpdateUserRequest;
import com.berktas.rentacar.core.validator.IsExistsValidator;
import com.berktas.rentacar.mapper.UserMapper;
import com.berktas.rentacar.model.dto.UserDto;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public UserDto save(SaveUserRequest saveUserRequest) {
        User user = userRepository.save(User.create(saveUserRequest));
        return userMapper.entityToDto(user);
    }


    @Override
    public UserDto update(Long id, UpdateUserRequest updateUserRequest) {
        User user = findById(id);
        return userMapper.entityToDto(user);
    }

    @Override
    public void delete(Long id) {
        IsExistsValidator.findById(userRepository, id);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.entityListToDtoList(userRepository.findAll());
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.entityToDto(findById(id));
    }


    @Override
    public void updateUserPassword(Long id, UpdatePasswordRequest request) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.save(user.updatePassword(request.getPassword()));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
