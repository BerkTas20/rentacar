package com.berktas.rentacar.spec;


import com.berktas.rentacar.model.entity.Car;

import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {

    public static Specification<Car> getByBrand(String brandName) {
        return (car, cq, cb) -> cb.equal(car.get("brandName"), car);
    }

    public static Specification<Car> getByColor(String colorName) {
        return (color, cq, cb) -> cb.like(color.get("colorName"), "%" + colorName + "%");
    }

}

