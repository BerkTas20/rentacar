package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.core.security.CustomUserDetails;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsManager implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username)  {
        List<User> all = userRepository.findAll();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(""));
        return new CustomUserDetails(user);
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}


