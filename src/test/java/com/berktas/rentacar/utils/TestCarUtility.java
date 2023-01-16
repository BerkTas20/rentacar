package com.berktas.rentacar.utils;


import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestCarUtility {
    @Autowired
    CarRepository carRepository;

    public Car getOrCreateTestCar() {
        Car car = carRepository.findById(1L).orElseGet(() -> {
            return carRepository.save(Car.create(SaveCarRequest.builder()
                            .carName("car_Test")
                            .dailyPrice(100)
                            .modelYear(2023)
                            .description("Test")
                    .build()));
        });
        return car;
    }

    public Car createTestCar(){
        return carRepository.save(Car.create(SaveCarRequest.builder()
                .carName("car_Test1")
                .dailyPrice(1000)
                .modelYear(2023)
                .description("Test_tEST")
                .build()));
    }
}
