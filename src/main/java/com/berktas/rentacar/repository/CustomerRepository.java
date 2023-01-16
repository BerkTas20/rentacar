package com.berktas.rentacar.repository;

import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
