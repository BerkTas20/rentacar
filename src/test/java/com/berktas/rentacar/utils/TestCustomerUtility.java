package com.berktas.rentacar.utils;


import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.controller.customer.SaveCustomerRequest;
import com.berktas.rentacar.core.validator.phone.Phone;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.embedded.Address;
import com.berktas.rentacar.model.entity.user.Customer;
import com.berktas.rentacar.repository.CarRepository;
import com.berktas.rentacar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class TestCustomerUtility {
    @Autowired
    CustomerRepository customerRepository;

    public Customer getOrCreateTestElevator() {
        Customer customer = customerRepository.findById(1L).orElseGet(() -> {
            Address address = new Address();
            String ts = TimeStampUtility.timeStampMSecondsString();
            address.setFullAddress("testAdres" + ts);
            address.setDistrict("MERKEZ"+ ts);
            address.setProvince("Denizli"+ ts);
            return customerRepository.save(Customer.create(SaveCustomerRequest.builder()
                            .username("Test_Customer"+ ts)
                            .companyName("Test_Company"+ ts)
                            .title("Customer"+ ts)
                            .address(address)
                            .password("123"+ ts)
                            .phone("5535011522")
                    .build()));
        });
        return customer;
    }

    public Customer createTestCustomer() {
        String ts = TimeStampUtility.timeStampMSecondsString();
        Address address = new Address();
        address.setFullAddress("testAdres" + ts);
        address.setDistrict("MERKEZ"+ ts);
        address.setProvince("Denizli"+ ts);
        return customerRepository.save(Customer.create(SaveCustomerRequest.builder()
                .username("Test_Customer"+ ts)
                .firstName("TestCustomer123" + ts)
                .companyName("Test_Company"+ ts)
                .title("Customer"+ ts)
                .address(address)
                .password("1234"+ ts)
                .phone("5535011595")
                .build()));
    }
}
