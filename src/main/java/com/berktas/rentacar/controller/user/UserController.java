package com.berktas.rentacar.controller.user;

import com.berktas.rentacar.business.abstracts.CarService;
import com.berktas.rentacar.business.abstracts.UserService;
import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.controller.car.UpdateCarRequest;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.model.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User")
//OnlyAdmin
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto save(@RequestBody SaveUserRequest saveUserRequest){
        return userService.save(saveUserRequest);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Long id, @RequestBody UpdateUserRequest updateUserRequest){
        return userService.update(id, updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    UserDto getById(@PathVariable(name = "id") Long id) {
        return userService.getById(id);
    }
}
