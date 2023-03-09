package com.berktas.rentacar.junit;

import com.berktas.rentacar.business.abstracts.CarService;
import com.berktas.rentacar.business.concretes.CarManager;
import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.controller.car.UpdateCarRequest;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RequiredArgsConstructor
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
}
