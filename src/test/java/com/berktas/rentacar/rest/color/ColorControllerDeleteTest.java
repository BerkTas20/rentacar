package com.berktas.rentacar.rest.color;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.model.entity.Color;
import com.berktas.rentacar.repository.ColorRepository;
import com.berktas.rentacar.utils.TestColorUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ColorControllerDeleteTest extends PlatformTestWithAuth {
    @Autowired
    ColorRepository colorRepository;

    @Autowired
    TestColorUtility testColorUtility;

    Color color;

    @Before
    public void before(){
        color = testColorUtility.createTestColor();
    }

    @Test
    public void deleteHappyPath200() {
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete(path(color.getId()))
                .then()
                .spec(ResponseSpec.isOkResponse());

        Assert.assertFalse("color should be deleted",
                colorRepository.findById(color.getId()).isPresent());
    }

    @Test
    public void test400WithStringId(){
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete("/color/abc")
                .then().log().all()
                .spec(ResponseSpec.isBadRequestResponse());
    }
    private String path(Long id){
        return String.format("/color/%d",id);
    }
}


