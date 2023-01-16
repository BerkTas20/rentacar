package com.berktas.rentacar.controller.rental;

import com.berktas.rentacar.business.abstracts.RentalService;
import com.berktas.rentacar.model.dto.RentalDetailDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")
@RequiredArgsConstructor
@Tag(name = "Rental")
//OnlyAdmin
public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public RentalDetailDto save(@RequestBody SaveRentalRequest saveRentalRequest,@PathVariable(name = "carId") Long carId){
        return rentalService.rent(saveRentalRequest, carId);
    }


}
