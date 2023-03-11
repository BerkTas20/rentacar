package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.model.entity.CurrentFuelPricesDto;

public interface DashboardService {
    CurrentFuelPricesDto getCurrentFuelPrices();
}
