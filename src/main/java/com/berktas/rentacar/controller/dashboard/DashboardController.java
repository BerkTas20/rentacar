package com.berktas.rentacar.controller.dashboard;

import com.berktas.rentacar.business.abstracts.DashboardService;
import com.berktas.rentacar.core.util.annotation.IsAuthenticated;
import com.berktas.rentacar.core.util.petroloffice.FuelResponse;
import com.berktas.rentacar.core.util.petroloffice.dto.BpOilPriceDto;
import com.berktas.rentacar.model.entity.CurrentFuelPricesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@IsAuthenticated
@CrossOrigin
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashBoardService;

    @GetMapping("/currentFuelPrices")
    public CurrentFuelPricesDto getCurrentFuelPrices() {
        return dashBoardService.getCurrentFuelPrices();
    }
}
