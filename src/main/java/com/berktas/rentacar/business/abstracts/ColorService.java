package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.controller.color.SaveColorRequest;
import com.berktas.rentacar.controller.color.UpdateColorRequest;
import com.berktas.rentacar.model.dto.ColorDto;
import com.berktas.rentacar.model.entity.Color;

import java.util.List;

public interface ColorService {
    ColorDto save(SaveColorRequest saveColorRequest);

    ColorDto update(Long id, UpdateColorRequest updateColorRequest);

    void delete(Long id);

    List<ColorDto> getAll();

    Color findById(Long id);

    ColorDto getById(Long id);
}
