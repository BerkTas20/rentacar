package com.berktas.rentacar.rest;

import com.berktas.rentacar.rest.brand.BrandControllerDeleteTest;
import com.berktas.rentacar.rest.brand.BrandControllerPostTest;
import com.berktas.rentacar.rest.brand.BrandControllerUpdateTest;
import com.berktas.rentacar.rest.car.CarControllerDeleteTest;
import com.berktas.rentacar.rest.car.CarControllerPostTest;
import com.berktas.rentacar.rest.car.CarControllerUpdateTest;
import com.berktas.rentacar.rest.color.ColorControllerDeleteTest;
import com.berktas.rentacar.rest.color.ColorControllerPostTest;
import com.berktas.rentacar.rest.color.ColorControllerUpdateTest;
import com.berktas.rentacar.rest.customer.CustomerControllerDeleteTest;
import com.berktas.rentacar.rest.customer.CustomerControllerPostTest;
import com.berktas.rentacar.rest.customer.CustomerControllerUpdateTest;
import com.berktas.rentacar.rest.login.AccountControllerPostLoginTest;
import com.berktas.rentacar.rest.user.UserControllerDeleteTest;
import com.berktas.rentacar.rest.user.UserControllerPostTest;
import com.berktas.rentacar.rest.user.UserControllerUpdateTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({

        //Login
        AccountControllerPostLoginTest.class,

       //Car
        CarControllerPostTest.class,
        CarControllerUpdateTest.class,
        CarControllerDeleteTest.class,

        //Customer
        CustomerControllerPostTest.class,
        CustomerControllerUpdateTest.class,
        CustomerControllerDeleteTest.class,

        //UserController
        UserControllerPostTest.class,
        UserControllerUpdateTest.class,
        UserControllerDeleteTest.class,

        //Brand
        BrandControllerPostTest.class,
        BrandControllerUpdateTest.class,
        BrandControllerDeleteTest.class,

        //Color
        ColorControllerPostTest.class,
        ColorControllerUpdateTest.class,
        ColorControllerDeleteTest.class,
})
public class ApplicationTests {

}
