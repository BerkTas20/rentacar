package com.berktas.rentacar.rest.customer;

import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.customer.SaveCustomerRequest;
import com.berktas.rentacar.model.entity.user.Customer;
import com.berktas.rentacar.repository.CustomerRepository;
import com.berktas.rentacar.utils.TimeStampUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class CustomerControllerPostTest extends PlatformTestWithAuth {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testPost() {
        SaveCustomerRequest request = createSaveCustomerRequest();
        Customer response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).post(path())
                .then()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(Customer.class);

        Assert.assertTrue("Should be in DB", customerRepository.findById(response.getId()).isPresent());

    }

    private String path() {
        return "/customer";
    }

    public SaveCustomerRequest createSaveCustomerRequest() {
        String ts = TimeStampUtility.timeStampString();
        SaveCustomerRequest request = SaveCustomerRequest.builder()
                .firstName("Customer_TestName")
                .lastName("Customer_TestLastName")
                .username("Customer_Test")
                .companyName("Customer_TestCompany")
                .build();
        return request;
    }
}