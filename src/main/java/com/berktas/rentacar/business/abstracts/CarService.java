package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.controller.car.UpdateCarRequest;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.enums.RentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;


public interface CarService {
    CarDto save(SaveCarRequest saveCarRequest);

    CarDto update(Long id, UpdateCarRequest updateCarRequest);

    void delete(Long id);

    List<CarDto> getAll();

    Car findById(Long id);

    CarDto getById(Long id);

    Page<CarDto> getAll(Pageable pageable);

    List<CarDto> getFilter(String brandName, String carName);

    void updateStatusByCar(Car car, RentStatus rentStatus);

    Set<Car> findActiveVehicleListByIdList(List<Long> idList);

}
