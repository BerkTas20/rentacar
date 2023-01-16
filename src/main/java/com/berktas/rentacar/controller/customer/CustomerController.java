package com.berktas.rentacar.controller.customer;

import com.berktas.rentacar.business.abstracts.CustomerService;
import com.berktas.rentacar.model.dto.CustomerDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Customer")
//OnlyAdmin
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerDto save(@RequestBody SaveCustomerRequest saveCustomerRequest){
        return customerService.save(saveCustomerRequest);
    }

    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable("id") Long id, @RequestBody UpdateCustomerRequest updateCustomerRequest){
        return customerService.update(id, updateCustomerRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        customerService.delete(id);
    }

    @GetMapping
    public List<CustomerDto> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    CustomerDto getById(@PathVariable(name = "id") Long id) {
        return customerService.getById(id);
    }
}
