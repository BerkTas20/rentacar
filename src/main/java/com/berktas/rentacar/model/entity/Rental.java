package com.berktas.rentacar.model.entity;

import com.berktas.rentacar.controller.rental.SaveRentalRequest;
import com.berktas.rentacar.model.entity.base.AbstractTimestampEntity;
import com.berktas.rentacar.model.entity.user.Customer;
import com.berktas.rentacar.model.entity.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
//@Setter(AccessLevel.PROTECTED)
public class Rental extends AbstractTimestampEntity {

    private LocalDateTime rentDate;
    private LocalDateTime returnDate;

    private Long customerId;

    private Long carId;

    public static Rental rent(SaveRentalRequest saveRentalRequest,Car car, User customer){
        Rental rental = new Rental();
        rental.setRentDate(saveRentalRequest.getRentDate());
        rental.setCustomerId(customer.getId());
        rental.setCarId(car.getId());
        return rental;
    }

    public Rental deliver(LocalDateTime localDate){
        this.setReturnDate(localDate);
        return this;
    }

}
