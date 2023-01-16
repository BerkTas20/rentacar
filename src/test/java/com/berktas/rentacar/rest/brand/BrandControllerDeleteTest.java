package com.berktas.rentacar.rest.brand;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.model.entity.Brand;
import com.berktas.rentacar.repository.BrandRepository;
import com.berktas.rentacar.utils.TestBrandUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BrandControllerDeleteTest extends PlatformTestWithAuth {
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
    public void deleteHappyPath200() {
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete(path(brand.getId()))
                .then()
                .spec(ResponseSpec.isOkResponse());

        Assert.assertFalse("car should be deleted",
                brandRepository.findById(brand.getId()).isPresent());
    }

    @Test
    public void test400WithStringId(){
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete("/car/abc")
                .then().log().all()
                .spec(ResponseSpec.isBadRequestResponse());
    }
    private String path(Long id){
        return String.format("/brand/%d",id);
    }
}


