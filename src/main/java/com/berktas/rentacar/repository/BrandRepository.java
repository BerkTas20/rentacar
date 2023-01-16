package com.berktas.rentacar.repository;

import com.berktas.rentacar.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BrandRepository extends JpaRepository<Brand, Long> {

}
