package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.CarService;
import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.controller.car.UpdateCarRequest;
import com.berktas.rentacar.core.validator.IsExistsValidator;
import com.berktas.rentacar.mapper.CarMapper;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.enums.CarStatus;
import com.berktas.rentacar.model.enums.RentStatus;
import com.berktas.rentacar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.berktas.rentacar.spec.CarSpecification.getByBrand;
import static com.berktas.rentacar.spec.CarSpecification.getByColor;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional
@RequiredArgsConstructor
public class CarManager implements CarService {
    private final CarMapper carMapper;
    private final CarRepository carRepository;

    @Override
    public CarDto save(SaveCarRequest saveCarRequest) {
        Car car = Car.create(saveCarRequest);
        return  carMapper.entityToDto(carRepository.save(car));
    }

    @Override
    public CarDto update(Long carId, UpdateCarRequest updateCarRequest) {
        Car car = findById(carId);
        return carMapper.entityToDto(car.update(updateCarRequest));
    }

    @Override
    public void delete(Long id) {
        IsExistsValidator.findById(carRepository, id);
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDto> getAll() {
        return carMapper.entityListToDtoList(carRepository.findAll());
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public CarDto getById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return carMapper.entityToDto(car);
    }

    @Override
    public Page<CarDto> getAll(Pageable pageable) {
        Pageable customPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().isEmpty() ? Sort.by("createdDateTime").descending() : pageable.getSort());
        Page<Car> carPage = carRepository.findAll(customPageable);
        List<CarDto> carDtoList = carMapper.entityListToDtoList(carPage.getContent());
        Page<CarDto> carDtoPage = new PageImpl<>(carDtoList, carPage.getPageable(), carPage.getTotalElements());
        return carDtoPage;

    }

    @Override
    public List<CarDto> getFilter(String brandName, String colorName) {
        return carMapper.entityListToDtoList(carRepository.findAll((Sort) where(getByBrand(brandName)).and(getByColor(colorName))))  ;
    }


    @Override
    public void updateStatusByVehicle(Car car, RentStatus rentStatus) {

        if (rentStatus == RentStatus.ACTIVE) {

            if (car.getRentStatus() == RentStatus.PASSIVE) {

                car.updateVehicleStatus(rentStatus);

                carRepository.save(car);
            }
        } else if (rentStatus == RentStatus.PASSIVE) {

            if (car.getRentStatus() == RentStatus.ACTIVE) {

                car.updateVehicleStatus(rentStatus);

                carRepository.save(car);
            } else {

                carRepository.save(car);
            }
        }
    }

    @Override
    public Set<Car> findActiveVehicleListByIdList(List<Long> idList) {
        Set<Car> vehicleSet = carRepository.findByIdIn(idList);
        vehicleSet = vehicleSet.stream().filter(vehicle -> vehicle.getCarStatus() == CarStatus.ACTIVE).collect(Collectors.toSet());

        if (vehicleSet.isEmpty()) {
            throw new EntityNotFoundException("Hiç Araç Bulunamamıştır.");
        }
        return vehicleSet;    }
}
