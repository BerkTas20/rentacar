package com.berktas.rentacar.repository;

import com.berktas.rentacar.model.dto.ColorDto;
import com.berktas.rentacar.model.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {

}
