package com.berktas.rentacar.controller.car;

import com.berktas.rentacar.business.abstracts.CarService;
import com.berktas.rentacar.model.dto.CarDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
@Tag(name = "Car")
//OnlyAdmin
public class CarController {
    private final CarService carService;

    @PostMapping
    public CarDto save(@RequestBody SaveCarRequest saveCarRequest){
        return carService.save(saveCarRequest);
    }

    @PutMapping("/{carId}")
    public CarDto update(@PathVariable("carId") Long id, @RequestBody UpdateCarRequest updateCarRequest){
        return carService.update(id, updateCarRequest);
    }

    @DeleteMapping("/{carId}")
    public void delete(@PathVariable("carId") Long id){
        carService.delete(id);
    }

    @GetMapping
    public List<CarDto> getAll(){
        return carService.getAll();
    }

    @GetMapping(value = "/{id}")
    public CarDto getById(@PathVariable(name = "id") Long id) {
        return carService.getById(id);
    }

    @GetMapping("filterByBrandAndColor")
    public List<CarDto> getFilterByBrandAndColor(@RequestParam String carName,@RequestParam String colorName ) {
        return carService.getFilter(carName, colorName);
    }
}
