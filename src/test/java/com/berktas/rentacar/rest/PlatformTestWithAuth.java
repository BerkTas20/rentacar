package com.berktas.rentacar.rest;


import com.berktas.rentacar.model.entity.user.Admin;
import com.berktas.rentacar.utils.TestUserUtility;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


public class PlatformTestWithAuth extends PlatformTest {


    @Autowired
    private TestUserUtility testUserUtility;

    private Admin admin;
    @PostConstruct
    public void beforeClassPlatformTestWithAuth(){
        admin = testUserUtility.getOrCreateTestAdmin();
    }

    public Admin getAdmin() {
        return testUserUtility.getOrCreateTestAdmin();
    }


}
