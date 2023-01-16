package com.berktas.rentacar.business.abstracts;


import com.berktas.rentacar.model.entity.CarLog;

public interface SaveGpsLogService {

    void saveGpsLog(CarLog carLog);
}
