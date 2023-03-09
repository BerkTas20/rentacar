package com.berktas.rentacar.rest.car;

import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.repository.CarRepository;
import com.berktas.rentacar.utils.TimeStampUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class CarControllerPostTest extends PlatformTestWithAuth {

    @Autowired
    CarRepository carRepository;

    @Test
    public void testPost() {
        SaveCarRequest request = createSaveCarRequest();
        CarDto response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).post(path())
                .then().log().all()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(CarDto.class);

        Assert.assertTrue("Should be in DB", carRepository.findById(response.getId()).isPresent());

    }

    private String path() {
        return "/car";
    }

    public SaveCarRequest createSaveCarRequest() {
        String ts = TimeStampUtility.timeStampString();
        SaveCarRequest request = SaveCarRequest.builder()
                .carName("BMW i4" + ts)
                .description("Test" + ts)
                .modelYear(2023)
                .dailyPrice(2)
                .licencePlate("6TRJ244")
                .build();
        return request;
    }
}