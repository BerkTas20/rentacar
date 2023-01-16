package com.berktas.rentacar.rest.login;

import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.account.LoginRequest;
import com.berktas.rentacar.utils.TestUserUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AccountControllerPostLoginTest extends PlatformTestWithAuth {

    @Autowired
    TestUserUtility testUserUtility;


    @Test
    public void testAdminLogin(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("12345");

        RequestSpec.given()
                .jsonRequest().body(loginRequest)
                .when().post(path())
                .then().log().all()
                .spec(ResponseSpec.isOkResponse());

    }

    private String path() {
        return "/account/login";
    }
}
