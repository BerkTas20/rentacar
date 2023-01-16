package com.berktas.rentacar.model.entity.user;

import com.berktas.rentacar.controller.customer.SaveCustomerRequest;
import com.berktas.rentacar.controller.customer.UpdateCustomerRequest;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.embedded.Address;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer extends User {

    @Column(unique = true, name = "customer_address")
    private Address address;

    private String title;

    public String companyName;

    @OneToMany //aynı anda ıkı kısı bır aracı kıralayamaz
    private List<Car> car;

    public static Customer create(SaveCustomerRequest saveCustomerRequest) {
        Customer customer = new Customer();
        customer.setAddress(saveCustomerRequest.getAddress());
        customer.setTitle(saveCustomerRequest.getTitle());
        customer.setLastName(saveCustomerRequest.getLastName());
        customer.setUsername(saveCustomerRequest.getUsername());
        customer.setPassword(saveCustomerRequest.getPassword());
        customer.setPhone(saveCustomerRequest.getPhone());
        customer.setEmail(saveCustomerRequest.getEmail());
        customer.setCompanyName(saveCustomerRequest.getCompanyName());

        return customer;
    }

    public Customer update(UpdateCustomerRequest updateCustomerRequest) {
        setAddress(updateCustomerRequest.getAddress());
        setFirstName(updateCustomerRequest.getFirstName());
        setLastName(updateCustomerRequest.getLastName());
        setUsername(updateCustomerRequest.getUsername());
        setPassword(updateCustomerRequest.getPassword());
        setPhone(updateCustomerRequest.getPhone());
        setEmail(updateCustomerRequest.getEmail());
        setCompanyName(updateCustomerRequest.getCompanyName());

        return this;
    }
}
