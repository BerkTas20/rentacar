package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.CustomerService;
import com.berktas.rentacar.controller.customer.SaveCustomerRequest;
import com.berktas.rentacar.controller.customer.UpdateCustomerRequest;
import com.berktas.rentacar.core.validator.IsExistsValidator;
import com.berktas.rentacar.mapper.CustomerMapper;
import com.berktas.rentacar.model.dto.CustomerDto;
import com.berktas.rentacar.model.entity.user.Customer;
import com.berktas.rentacar.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto save(SaveCustomerRequest saveCustomerRequest) {
        Customer customer = Customer.create(saveCustomerRequest);
        return customerMapper.entityToDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto update(Long id, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = findById(id);
        return customerMapper.entityToDto(customer.update(updateCustomerRequest));
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerMapper.entityListToDtoList(customerRepository.findAll());
    }


    @Override
    public void delete(Long id) {
        IsExistsValidator.findById(customerRepository, id);
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public CustomerDto getById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return customerMapper.entityToDto(customer);
    }
}
