package com.berktas.rentacar.business.abstracts;


import com.berktas.rentacar.model.dto.CarLogHistoryDto;
import com.berktas.rentacar.model.dto.VehicleLogHistoryDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface CarLogService {


    Map<Long, List<CarLogHistoryDto>> findByDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime, List<Long> carIdList);


    Map<Long, CarLogHistoryDto> findTimeLineByDateAndVehicleIdList(LocalDateTime logDateTime, List<Long> carIdList);

    VehicleLogHistoryDto findTimeLineByDateAndCarId(LocalDateTime logDateTime, Long carId);

    List<CarLogHistoryDto> filterCarLogHistoryForIgnitionFalseStatus(List<CarLogHistoryDto> carLogHistoryDtoList);

    List<CarLogHistoryDto> filterCarLogHistoryForIdle(List<CarLogHistoryDto> carLogHistoryDtoList);

    List<CarLogHistoryDto> filterCarLogHistoryFacade(List<CarLogHistoryDto> vehicleLogHistoryDtoList);

    Map<Long, List<CarLogHistoryDto>> findByDateRangeToMap(LocalDateTime startDateTime, LocalDateTime endDateTime, List<Long> carIdList);

    Map<Long, CarLogHistoryDto> findTimeLineByDateAndVehicleIdListToMap(LocalDateTime logDateTime, List<Long> carIdList);

    List<CarLogHistoryDto> historyReport(LocalDateTime startDateTime, LocalDateTime endDateTime, List<Long> carIdList);


    List<CarLogHistoryDto> historyOfCar(LocalDateTime startDateTime, LocalDateTime endDateTime, Long carId);
}
