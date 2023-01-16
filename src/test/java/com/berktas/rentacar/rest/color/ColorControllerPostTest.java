package com.berktas.rentacar.rest.color;

import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.color.SaveColorRequest;
import com.berktas.rentacar.model.entity.Color;
import com.berktas.rentacar.repository.ColorRepository;
import com.berktas.rentacar.utils.TimeStampUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class ColorControllerPostTest extends PlatformTestWithAuth {

    @Autowired
    ColorRepository colorRepository;

    @Test
    public void testPost() {
        SaveColorRequest request = createSaveColorRequest();
        Color response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).post(path())
                .then().log().all()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(Color.class);

        Assert.assertTrue("Should be in DB", colorRepository.findById(response.getId()).isPresent());

    }

    private String path() {
        return "/color";
    }

    public SaveColorRequest createSaveColorRequest() {
        String ts = TimeStampUtility.timeStampString();
        SaveColorRequest request = SaveColorRequest.builder()
                .colorName("Test_Color" + ts)
                .build();
        return request;
    }
}