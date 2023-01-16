package com.berktas.rentacar.utils;


import com.berktas.rentacar.controller.brand.SaveBrandRequest;
import com.berktas.rentacar.controller.color.SaveColorRequest;
import com.berktas.rentacar.model.entity.Brand;
import com.berktas.rentacar.model.entity.Color;
import com.berktas.rentacar.repository.BrandRepository;
import com.berktas.rentacar.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestColorUtility {
    @Autowired
    ColorRepository colorRepository;

    public Color getOrCreateTestColor() {
        Color color = colorRepository.findById(1L).orElseGet(() -> {
            return colorRepository.save(Color.create(SaveColorRequest.builder()
                    .colorName("ColorName_Test")
                    .build()));
        });
        return color;
    }

    public Color createTestColor(){
        return colorRepository.save(Color.create(SaveColorRequest.builder()
                .colorName("ColorName_Test1")
                .build()));
    }
}
