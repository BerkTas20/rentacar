package com.berktas.rentacar.integration;

import com.berktas.rentacar.business.abstracts.CarService;
import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarIntegrationTest {
    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

//    @Test
//    public void testSave() {
//        // create a new car
//        SaveCarRequest saveCarRequest = SaveCarRequest.builder()
//                .carName("Tesla")
//                .dailyPrice(1.2)
//                .licencePlate("test1")
//                .modelYear(2020)
//
//                .build();
//
//
//        // save the car
//        CarDto savedCar = carService.save(saveCarRequest);
//
//        // assert that the car was saved
//        assertNotNull(savedCar);
//        assertNotNull(savedCar.getId());
//        assertEquals("Tesla", savedCar.getCarName());
//        assertEquals(2020, savedCar.getModelYear());
//
//        // assert that the car was saved in the database
//        Optional<Car> carInDb = carRepository.findById(savedCar.getId());
//        assertTrue(carInDb.isPresent());
//        assertEquals("Tesla", carInDb.get().getCarName());
//        assertEquals(2020, carInDb.get().getYear());
//    }
}
