package com.berktas.rentacar.rest.brand;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.brand.UpdateBrandRequest;
import com.berktas.rentacar.model.dto.BrandDto;
import com.berktas.rentacar.model.entity.Brand;
import com.berktas.rentacar.repository.BrandRepository;
import com.berktas.rentacar.utils.TestBrandUtility;
import com.berktas.rentacar.utils.TimeStampUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BrandControllerUpdateTest extends PlatformTestWithAuth {

    @Autowired
    BrandRepository brandRepository;
    @Autowired
    TestBrandUtility testBrandUtility;

    Brand brand;

    @Before
    public void before(){
       brand = testBrandUtility.createTestBrand();
    }

    @Test
    public void testUpdate200() {
        UpdateBrandRequest request = updateCarRequest();
        BrandDto response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).put(path(Math.toIntExact(brand.getId())))
                .then()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(BrandDto.class);

        Assert.assertEquals(request.getBrandName(), response.getBrandName());
    }

    @Test
    public void test400WithStringId(){
        UpdateBrandRequest request = updateCarRequest();
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().put("/car/abc")
                .then()
                .spec(ResponseSpec.isBadRequestResponse());
    }

    private String path(int id){
        return String.format("/brand/%d",id);
    }

    public UpdateBrandRequest updateCarRequest(){
        String ts = TimeStampUtility.timeStampMSecondsString();
        UpdateBrandRequest request = UpdateBrandRequest.builder()
                .brandName("Porsche")
                .build();
        return request;
    }

}
