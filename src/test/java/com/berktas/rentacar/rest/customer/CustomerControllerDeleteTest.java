package com.berktas.rentacar.rest.customer;


import com.berktas.rentacar.rest.PlatformTestWithAuth;
import com.berktas.rentacar.rest.RequestSpec;
import com.berktas.rentacar.rest.ResponseSpec;
import com.berktas.rentacar.model.entity.user.Customer;
import com.berktas.rentacar.repository.CustomerRepository;
import com.berktas.rentacar.utils.TestCustomerUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CustomerControllerDeleteTest extends PlatformTestWithAuth {
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
    public void deleteHappyPath200() {
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete(path(customer.getId()))
                .then()
                .spec(ResponseSpec.isOkResponse());

        Assert.assertFalse("customer should be deleted",
                customerRepository.findById(customer.getId()).isPresent());
    }

    @Test
    public void test400WithStringId(){
        RequestSpec.given()
                .authenticated(getAdmin()).jsonRequest().delete("/customer/abc")
                .then().log().all()
                .spec(ResponseSpec.isBadRequestResponse());
    }
    private String path(Long id){
        return String.format("/customer/%d",id);
    }
}


