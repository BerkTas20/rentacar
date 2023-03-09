package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.CarService;
import com.berktas.rentacar.business.abstracts.CustomerService;
import com.berktas.rentacar.business.abstracts.RentalService;
import com.berktas.rentacar.controller.rental.SaveRentalRequest;
import com.berktas.rentacar.core.config.SecurityConfig;
import com.berktas.rentacar.core.util.SpringContext;
import com.berktas.rentacar.mapper.RentalMapper;
import com.berktas.rentacar.model.dto.RentalDetailDto;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.Rental;
import com.berktas.rentacar.model.entity.user.Customer;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.model.enums.RentStatus;
import com.berktas.rentacar.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalManager implements RentalService {

    private final RentalMapper rentalMapper;
    private final RentalRepository rentalRepository;
    private final CustomerService customerService;
    private final CarService carService;


    @Override
    public RentalDetailDto rent(SaveRentalRequest saveRentalRequest, Long carId) {
        Car car = carService.findById(carId);
        User user = SpringContext.getCurrentUser();
        Rental rental = Rental.rent(saveRentalRequest, car, user);
        car.rentStatus = RentStatus.PASSIVE; //araç artık pasif, çünkü kiralandı.
        return rentalMapper.entityToDto(rentalRepository.save(rental));
    }

    @Override
    public RentalDetailDto deliver(Long id, LocalDateTime localDate) {
        return null;
    }
}
