package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.ColorService;
import com.berktas.rentacar.controller.color.SaveColorRequest;
import com.berktas.rentacar.controller.color.UpdateColorRequest;
import com.berktas.rentacar.core.validator.IsExistsValidator;
import com.berktas.rentacar.mapper.ColorMapper;
import com.berktas.rentacar.model.dto.ColorDto;
import com.berktas.rentacar.model.entity.Color;
import com.berktas.rentacar.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorManager implements ColorService {
    private final ColorRepository colorRepository;

    private final ColorMapper colorMapper;


    @Override
    public ColorDto save(SaveColorRequest saveColorRequest) {
        Color color = Color.create(saveColorRequest);
        return  colorMapper.entityToDto(colorRepository.save(color));
    }

    @Override
    public ColorDto update(Long id, UpdateColorRequest updateColorRequest) {
        Color color = findById(id);
        return colorMapper.entityToDto(color.update(updateColorRequest));
    }

    @Override
    public void delete(Long id) {
        IsExistsValidator.findById(colorRepository, id);
        colorRepository.deleteById(id);
    }


    @Override
    public List<ColorDto> getAll() {
        return colorMapper.entityListToDtoList(colorRepository.findAll());
    }

    @Override
    public Color findById(Long id) {
        return colorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ColorDto getById(Long id) {
        Color color = colorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return  colorMapper.entityToDto(color);
    }
}
