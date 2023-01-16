package com.berktas.rentacar.rest.brand;

import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.brand.SaveBrandRequest;
import com.berktas.rentacar.model.dto.BrandDto;
import com.berktas.rentacar.repository.BrandRepository;
import com.berktas.rentacar.utils.TimeStampUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class BrandControllerPostTest extends PlatformTestWithAuth {

    @Autowired
    BrandRepository brandRepository;

    @Test
    public void testPost() {
        SaveBrandRequest request = createSaveBrandRequest();
        BrandDto response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).post(path())
                .then().log().all()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(BrandDto.class);

        Assert.assertTrue("Should be in DB", brandRepository.findById(response.getId()).isPresent());

    }

    private String path() {
        return "/brand";
    }

    public SaveBrandRequest createSaveBrandRequest() {
        String ts = TimeStampUtility.timeStampString();
        SaveBrandRequest request = SaveBrandRequest.builder()
                .brandName("Test_Brand" + ts)
                .build();
        return request;
    }
}