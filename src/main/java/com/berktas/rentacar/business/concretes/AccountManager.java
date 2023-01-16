package com.berktas.rentacar.business.concretes;


import com.berktas.rentacar.business.abstracts.AccountService;
import com.berktas.rentacar.controller.account.LoginRequest;
import com.berktas.rentacar.controller.account.LoginResponse;
import com.berktas.rentacar.core.exception.IncorrectEntryException;
import com.berktas.rentacar.core.exception.validator.UserDisabledException;
import com.berktas.rentacar.core.security.CustomUserDetails;
import com.berktas.rentacar.core.security.JwtTokenUtil;
import com.berktas.rentacar.core.util.SpringContext;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountManager implements AccountService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
//    private final SecurityContextUtil securityContextUtil;


    @Override
    public LoginResponse login(LoginRequest customerLoginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerLoginRequest.getUsername(), customerLoginRequest.getPassword()));
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User currentUser = customUserDetails.getUser();
            final String token = jwtTokenUtil.generate(customUserDetails, customerLoginRequest.isRememberMe());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);
            HttpServletRequest request = SpringContext.getCurrentHttpRequest().get();
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
            return new LoginResponse(currentUser.getUsername(), token);
        } catch (BadCredentialsException badCredentialsException) {
            throw new IncorrectEntryException("");
        } catch (DisabledException disabledException) {
            throw new UserDisabledException("");
        }
    }


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //@Todo Do nothing for now - Delete Firebase Token later
    }


}
