package com.berktas.rentacar.repository;

import com.berktas.rentacar.model.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, String> {
}
