package com.berktas.rentacar.rest.color;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.color.UpdateColorRequest;
import com.berktas.rentacar.model.dto.ColorDto;
import com.berktas.rentacar.model.entity.Color;
import com.berktas.rentacar.repository.ColorRepository;
import com.berktas.rentacar.utils.TestColorUtility;
import com.berktas.rentacar.utils.TimeStampUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ColorControllerUpdateTest extends PlatformTestWithAuth {

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
    public void testUpdate200() {
        UpdateColorRequest request = updateColorRequest();
        ColorDto response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).put(path(Math.toIntExact(color.getId())))
                .then()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(ColorDto.class);

        Assert.assertEquals(request.getColorName(), response.getColorName());
    }

    @Test
    public void test400WithStringId(){
        UpdateColorRequest request = updateColorRequest();
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().put("/color/abc")
                .then()
                .spec(ResponseSpec.isBadRequestResponse());
    }
    private String path(int id){
        return String.format("/color/%d",id);
    }

    public UpdateColorRequest updateColorRequest(){
        String ts = TimeStampUtility.timeStampMSecondsString();
        UpdateColorRequest request = UpdateColorRequest.builder()
                .colorName("Kırmızı")
                .build();
        return request;
    }

}
