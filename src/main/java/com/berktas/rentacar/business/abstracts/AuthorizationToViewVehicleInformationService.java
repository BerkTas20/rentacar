package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.user.User;

public interface AuthorizationToViewVehicleInformationService {

    Boolean isItMyVehicleWebSocket(Car car, User currentUser);

    Boolean isItMyVehicle(Car vehicle);

}
