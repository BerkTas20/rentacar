package com.berktas.rentacar.rest.user;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.repository.UserRepository;
import com.berktas.rentacar.utils.TestUserUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserControllerDeleteTest extends PlatformTestWithAuth {
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
    public void deleteHappyPath200() {
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete(path(user.getId()))
                .then()
                .spec(ResponseSpec.isOkResponse());

        Assert.assertFalse("user should be deleted",
                userRepository.findById(user.getId()).isPresent());
    }

    @Test
    public void test400WithStringId(){
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete("/user/abc")
                .then().log().all()
                .spec(ResponseSpec.isBadRequestResponse());
    }
    private String path(Long id){
        return String.format("/user/%d",id);
    }
}


