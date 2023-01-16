package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.controller.customer.SaveCustomerRequest;
import com.berktas.rentacar.controller.customer.UpdateCustomerRequest;
import com.berktas.rentacar.model.dto.CustomerDto;
import com.berktas.rentacar.model.entity.user.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDto save(SaveCustomerRequest saveCustomerRequest);

    CustomerDto update(Long id, UpdateCustomerRequest updateCustomerRequest);

    List<CustomerDto> getAll();

    void delete(Long id);

    Customer findById(Long id);

    CustomerDto getById(Long id);
}
