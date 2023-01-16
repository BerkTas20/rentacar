package com.berktas.rentacar.rest.user;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.user.UpdateUserRequest;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.repository.UserRepository;
import com.berktas.rentacar.utils.TestUserUtility;
import com.berktas.rentacar.utils.TimeStampUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserControllerUpdateTest extends PlatformTestWithAuth {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TestUserUtility testUserUtility;

    User user;

    @Before
    public void before(){
        user = testUserUtility.getOrCreateTestUser();
    }

    @Test
    public void testUpdate200() {
        UpdateUserRequest request = updateUserRequest();
        User response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).put(path(Math.toIntExact(user.getId())))
                .then()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(User.class);
    }

    @Test
    public void test400WithStringId(){
        UpdateUserRequest request = updateUserRequest();
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().put("/user/abc")
                .then()
                .spec(ResponseSpec.isBadRequestResponse());
    }


    private String path(int id){
        return String.format("/user/%d",id);
    }

    public UpdateUserRequest updateUserRequest(){
        String ts = TimeStampUtility.timeStampMSecondsString();
        UpdateUserRequest request = UpdateUserRequest.builder()
                .username("Morgoth" + ts)
                .firstName("Ak" + ts)
                .lastName("Gandalf" + ts)
                .password("789" + ts)
                .email("morgoth@gmail.com"  + ts)
                .build();
        return request;
    }

}
