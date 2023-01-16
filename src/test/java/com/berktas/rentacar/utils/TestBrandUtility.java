package com.berktas.rentacar.utils;


import com.berktas.rentacar.controller.brand.SaveBrandRequest;
import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.model.entity.Brand;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.repository.BrandRepository;
import com.berktas.rentacar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestBrandUtility {
    @Autowired
    BrandRepository brandRepository;

    public Brand getOrCreateTestCar() {
        Brand brand = brandRepository.findById(1L).orElseGet(() -> {
            return brandRepository.save(Brand.create(SaveBrandRequest.builder()
                    .brandName("BrandName_Test")
                    .build()));
        });
        return brand;
    }

    public Brand createTestBrand(){
        return brandRepository.save(Brand.create(SaveBrandRequest.builder()
                .brandName("BrandName_Test1")
                .build()));
    }
}
