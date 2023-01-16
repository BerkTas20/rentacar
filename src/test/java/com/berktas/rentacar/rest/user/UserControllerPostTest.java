package com.berktas.rentacar.rest.user;

import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.user.SaveUserRequest;
import com.berktas.rentacar.model.dto.UserDto;
import com.berktas.rentacar.model.enums.Role;
import com.berktas.rentacar.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserControllerPostTest extends PlatformTestWithAuth {

    @Autowired
    UserRepository userRepository;

    @Test
    public void happyPath() {
        SaveUserRequest request = createSaveUserRequest();
        UserDto response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).post(path())
                .then()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(UserDto.class);

        Assert.assertTrue("User has not been created", userRepository.findById(response.getId()).isPresent());
        Assert.assertEquals(request.getUsername(), response.getUsername());
        Assert.assertEquals(request.getEmail(), response.getEmail());
        Assert.assertEquals(request.getRole(), response.getRole());
    }

//    @Test()
//    public void unauthorizedDueNoToken() { //değerlerin tümü girilmezse authorize olmuyor.
//        SaveUserRequest request = createSaveUserRequest();
//        RequestSpec.given()
//                .jsonRequest().body(request).post(path())
//                .then()
//                .spec(ResponseSpec.isUnauthorizedResponse());
//    }

//    @Test()
//    public void noPermissionDueWrongUserRole() {
//        SaveUserRequest request = createSaveUserRequest();
//        RequestSpec.given()
//                .authenticated(getUser()).jsonRequest().body(request).post(path())
//                .then()
//                .spec(ResponseSpec.isForbiddenResponse());
//    }

    private String path() {
        return "/user";
    }

    public SaveUserRequest createSaveUserRequest() {
        SaveUserRequest request = SaveUserRequest.builder()
                .role(Role.ROLE_ADMIN)
                .email("rentacar@gmail.com")
                .firstName("rentacar1_test")
                .username("rentacar")
                .password("12345")
                .build();
        return request;
    }
}

