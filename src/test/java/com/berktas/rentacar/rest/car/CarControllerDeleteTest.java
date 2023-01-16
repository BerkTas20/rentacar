package com.berktas.rentacar.rest.car;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.repository.CarRepository;
import com.berktas.rentacar.utils.TestCarUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarControllerDeleteTest extends PlatformTestWithAuth {
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
    public void deleteHappyPath200() {
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete(path(car.getId()))
                .then()
                .spec(ResponseSpec.isOkResponse());

        Assert.assertFalse("car should be deleted",
                carRepository.findById(car.getId()).isPresent());
    }


    @Test
    public void test400WithStringId(){
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete("/car/abc")
                .then().log().all()
                .spec(ResponseSpec.isBadRequestResponse());
    }
    private String path(Long id){
        return String.format("/car/%d",id);
    }
}


