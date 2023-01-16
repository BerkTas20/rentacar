package com.berktas.rentacar.junit;

import com.berktas.rentacar.business.abstracts.CarService;
import com.berktas.rentacar.business.concretes.CarManager;
import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.controller.car.UpdateCarRequest;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.repository.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CarServiceTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarManager carManager;
    private Car car;
    private CarDto carDto;

    @Before
    public void init(){
        car = Car.builder()
                .carName("unitTest")
                .dailyPrice(1.2)
                .modelYear(2023)
                .description("Test")
                .build();
        carDto = CarDto.builder()
                .carName(car.getCarName())
                .dailyPrice(car.getDailyPrice())
                .modelYear(car.getModelYear())
                .description(car.getDescription())
                .build();
    }

    @Test
    public void createCar(){
        SaveCarRequest saveCarRequest = SaveCarRequest.builder()
                .carName("test")
                .dailyPrice(1.2)
                .licencePlate("testPlate")
                .modelYear(2000)
                .build();


        CarDto carDto1 = carManager.save(saveCarRequest);

//        assertEquals(Optional.of(1L), Optional.ofNullable(carDto1.getId()));
        assertEquals(saveCarRequest.getCarName(), carDto1.getCarName());
    }
//    @Test
//    public void testUpdateCar() {
//        Long carId = 1L;
//        UpdateCarRequest updateCarRequest = UpdateCarRequest.builder()
//                .carName("test")
//                .dailyPrice(1.2)
//                .licencePlate("testPlate")
//                .modelYear(2000)
//                .build();
//
//        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
//
//        updateCarRequest.setCarName("New Name");
//
//        CarDto updatedCarDto = carManager.update(carId, updateCarRequest);
//        assertEquals(updatedCarDto.getCarName(), "New Name");
//    }
//
//    @Test
//    public void deleteCar(){
//        Car car1 = new Car();
//        car1.setCarName("test");
//        when(carRepository.save(car1)).thenReturn(car1);
//        verify(carRepository, times(1)).deleteById(car1.getId());
//    }
//
//    @Test
//    public void testGetById() {
//        // setup data and mocks
//        Long id = 1L;
//        Car car = new Car();
//        car.setId(id);
//        car.setCarName("Tesla");
//        car.setDescription("Model S");
//        car.setYear("2020");
//        when(carRepository.findById(id)).thenReturn(Optional.of(car));
//
//        verify(carRepository).findById(id);
//    }
    
}
