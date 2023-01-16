package com.berktas.rentacar.controller.brand;

import com.berktas.rentacar.business.abstracts.BrandService;
import com.berktas.rentacar.model.dto.BrandDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Tag(name = "Brand")
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    public BrandDto save(@RequestBody SaveBrandRequest saveBrandRequest){
        return brandService.save(saveBrandRequest);
    }

    @PutMapping("/{id}")
    public BrandDto update(@PathVariable("id") Long id, @RequestBody UpdateBrandRequest updateBrandRequest){
        return brandService.update(id, updateBrandRequest);
    }

    @GetMapping
    public List<BrandDto> getAll(){
        return brandService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        brandService.delete(id);
    }

    @GetMapping(value = "/{id}")
    BrandDto getById(@PathVariable(name = "id") Long id) {
        return brandService.getById(id);
    }
}
