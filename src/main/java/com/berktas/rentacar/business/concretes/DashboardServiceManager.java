package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.DashboardService;
import com.berktas.rentacar.core.util.opet.OpetManager;
import com.berktas.rentacar.model.entity.CurrentFuelPricesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DashboardServiceManager implements DashboardService {
    private final OpetManager opetManager;
    @Override
    public CurrentFuelPricesDto getCurrentFuelPrices() {
        return opetManager.getPrices();
    }
}
