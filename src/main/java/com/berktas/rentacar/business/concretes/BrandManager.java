package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.BrandService;
import com.berktas.rentacar.controller.brand.SaveBrandRequest;
import com.berktas.rentacar.controller.brand.UpdateBrandRequest;
import com.berktas.rentacar.core.validator.IsExistsValidator;
import com.berktas.rentacar.mapper.BrandMapper;
import com.berktas.rentacar.model.dto.BrandDto;
import com.berktas.rentacar.model.entity.Brand;
import com.berktas.rentacar.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandManager implements BrandService {

    private final BrandMapper brandMapper;

    private final BrandRepository brandRepository;


    @Override
    public BrandDto save(SaveBrandRequest saveBrandRequest) {
        Brand brand = Brand.create(saveBrandRequest);
        return brandMapper.entityToDto(brandRepository.save(brand));
    }

    @Override
    public BrandDto update(Long id, UpdateBrandRequest updateBrandRequest) {
        Brand brand = findById(id);
        return brandMapper.entityToDto(brand.update(updateBrandRequest));
    }

    @Override
    public List<BrandDto> getAll() {
        return brandMapper.entityListToDtoList(brandRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        IsExistsValidator.findById(brandRepository, id);
        brandRepository.deleteById(id);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElseThrow(EntityNotFoundException::new);    }

    @Override
    public BrandDto getById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return brandMapper.entityToDto(brand);
    }
}
