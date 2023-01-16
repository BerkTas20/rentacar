package com.berktas.rentacar.rest.car;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.car.UpdateCarRequest;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.repository.CarRepository;
import com.berktas.rentacar.utils.TestCarUtility;
import com.berktas.rentacar.utils.TimeStampUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarControllerUpdateTest extends PlatformTestWithAuth {

    @Autowired
    CarRepository carRepository;
    @Autowired
    TestCarUtility testCarUtility;

    Car car;

    @Before
    public void before(){
       car = testCarUtility.createTestCar();
    }

    @Test
    public void testUpdate200() {
        UpdateCarRequest request = updateCarRequest();
        CarDto response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).put(path(Math.toIntExact(car.getId())))
                .then()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(CarDto.class);

        Assert.assertEquals(request.getCarName(), response.getCarName());
        Assert.assertEquals(request.getDailyPrice(), response.getDailyPrice(),0001);
        Assert.assertEquals(request.getDailyPrice(), response.getDailyPrice(),0.001);
        Assert.assertEquals(request.getDailyPrice(), response.getDailyPrice(),0.001);
    }

    @Test
    public void test400WithStringId(){
        UpdateCarRequest request = updateCarRequest();
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().put("/car/abc")
                .then()
                .spec(ResponseSpec.isBadRequestResponse());
    }
    private String path(int id){
        return String.format("/car/%d",id);
    }

    public UpdateCarRequest updateCarRequest(){
        String ts = TimeStampUtility.timeStampMSecondsString();
        UpdateCarRequest request = UpdateCarRequest.builder()
                .carName("Honda")
                .dailyPrice(200)
                .modelYear(2020)
                .description("tramer kaydı var")
                .build();
        return request;
    }

}
