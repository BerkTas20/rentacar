package com.berktas.rentacar.business.concretes;


import com.berktas.rentacar.business.abstracts.AuthorizationToViewVehicleInformationService;
import com.berktas.rentacar.core.util.SpringContext;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorizationToViewVehicleInformationManager implements AuthorizationToViewVehicleInformationService {

    private final EntityManager entityManager;
//    private final SecurityContextUtil securityContextUtil;


    @Override
    public Boolean isItMyVehicleWebSocket(Car car, User currentUser) {
        return Objects.equals(currentUser.getId(), car.getId());
    }

    @Override
    public Boolean isItMyVehicle(Car car) {

        User currentUser = SpringContext.getCurrentUser();
        return Objects.equals(currentUser.getId(), car.getId());
    }
}
