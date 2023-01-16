package com.berktas.rentacar.repository;

import com.berktas.rentacar.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
