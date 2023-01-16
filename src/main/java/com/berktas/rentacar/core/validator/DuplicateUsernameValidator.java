package com.berktas.rentacar.core.validator;

import com.berktas.rentacar.core.exception.validator.DuplicateUsernameException;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
//@RequiredArgsConstructor
//public class DuplicateUsernameValidator implements Validator<String> {
//
//    private final UserRepository userRepository;
//
////    @Override
////    public void validate(String username) {
////        Optional<User> user = userRepository.findByUsername(username);
////        if (user.isPresent()) {
////            throw new DuplicateUsernameException("Bu kullanıcı adı daha önce alınmış.");
////        }
////    }
//
//}
