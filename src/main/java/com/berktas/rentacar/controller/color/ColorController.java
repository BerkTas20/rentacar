package com.berktas.rentacar.controller.color;

import com.berktas.rentacar.business.abstracts.ColorService;
import com.berktas.rentacar.model.dto.ColorDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
@Tag(name = "Color")
public class ColorController {

    private final ColorService colorService;

    @PostMapping
    public ColorDto save(@RequestBody SaveColorRequest saveColorRequest){
        return colorService.save(saveColorRequest);
    }

    @PutMapping("/{id}")
    public ColorDto update(@RequestBody UpdateColorRequest updateColorRequest,@PathVariable(name = "id") Long id){
        return colorService.update(id, updateColorRequest);
    }

    @GetMapping
    public List<ColorDto> getAll(){
        return colorService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        colorService.delete(id);
    }

    @GetMapping(value = "/{id}")
    ColorDto getById(@PathVariable(name = "id") Long id) {
        return colorService.getById(id);
    }
}
