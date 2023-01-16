package com.berktas.rentacar.rest.customer;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.controller.customer.UpdateCustomerRequest;
import com.berktas.rentacar.model.dto.CustomerDto;
import com.berktas.rentacar.model.entity.user.Customer;
import com.berktas.rentacar.repository.CustomerRepository;
import com.berktas.rentacar.utils.TestCustomerUtility;
import com.berktas.rentacar.utils.TimeStampUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CustomerControllerUpdateTest extends PlatformTestWithAuth {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TestCustomerUtility testCustomerUtility;

    Customer customer;

    @Before
    public void before(){
        customer = testCustomerUtility.createTestCustomer();
    }

    @Test
    public void testUpdate200() {
        UpdateCustomerRequest request = updateCustomerRequest();
        CustomerDto response = RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().body(request).put(path(Math.toIntExact(customer.getId())))
                .then()
                .spec(ResponseSpec.isOkResponse())
                .extract().body().as(CustomerDto.class);

        Assert.assertEquals(request.getFirstName(), response.getFirstName());
        Assert.assertEquals(request.getLastName(), response.getLastName());
        Assert.assertEquals(request.getCompanyName(), response.getCompanyName());
    }

    @Test
    public void test400WithStringId(){
        UpdateCustomerRequest request = updateCustomerRequest();
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().put("/customer/abc")
                .then()
                .spec(ResponseSpec.isBadRequestResponse());
    }

    private String path(int id){
        return String.format("/customer/%d",id);
    }

    public UpdateCustomerRequest updateCustomerRequest(){
        String ts = TimeStampUtility.timeStampMSecondsString();
        UpdateCustomerRequest request = UpdateCustomerRequest.builder()
                .companyName("Aselsis")
                .firstName("John")
                .lastName("Cena")
                .username("Customer")
                .build();
        return request;
    }

}
