package com.berktas.rentacar.core.util;


import com.berktas.rentacar.controller.account.MeResponse;
import com.berktas.rentacar.core.exception.UnauthorizedTransactionException;
import com.berktas.rentacar.core.security.CustomUserDetails;
import com.berktas.rentacar.model.entity.user.Admin;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.model.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@Slf4j

public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * Returns the Spring managed bean instance of the given class type if it exists.
     * Returns null otherwise.
     *
     * @param beanClass
     * @return
     */
    public static <T extends Object> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    public static <T extends Object> T getBean(String name, Class<T> beanClass) {
        return context.getBean(name, beanClass);
    }

    public static Optional<HttpServletRequest> getCurrentHttpRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }

    public static User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    public static MeResponse getMe() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return MeResponse.builder()
                .firstName(userDetails.getUser().getFirstName())
                .lastName(userDetails.getUser().getLastName())
                .username(userDetails.getUsername())
                .role(userDetails.getRole())
                .build();
    }

    public Admin getAdmin() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails.getRole() == Role.ROLE_ADMIN) {

            return (Admin) userDetails.getUser();

        } else {
            throw new UnauthorizedTransactionException("Yanlış ROL!!!!!", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContext.context = context;
    }

}