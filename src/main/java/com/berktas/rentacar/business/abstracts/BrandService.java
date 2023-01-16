package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.controller.brand.SaveBrandRequest;
import com.berktas.rentacar.controller.brand.UpdateBrandRequest;
import com.berktas.rentacar.model.dto.BrandDto;
import com.berktas.rentacar.model.entity.Brand;

import java.util.List;

public interface BrandService {
    BrandDto save(SaveBrandRequest brandRequest);

    BrandDto update(Long id,UpdateBrandRequest updateBrandRequest);

    List<BrandDto> getAll();

    void delete(Long id);

    Brand findById(Long id);

    BrandDto getById(Long id);
}
